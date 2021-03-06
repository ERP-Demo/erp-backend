package cn.shiying.govern.gateway.filter;

import cn.shiying.common.dto.Result;
import cn.shiying.common.enums.ErrorEnum;
import cn.shiying.common.utils.CookieUtil;
import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import cn.shiying.govern.gateway.service.AuthService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 身份校验过虑器
 * @author Administrator
 * @version 1.0
 **/

@Component
public class LoginFilter extends ZuulFilter {

    private static final String[] LOGIN_URI = {"/api/auth/userlogin","/api/Department/Department/getAll","/api/users_department/Department/allDepartment","/api/users_department/Department/getUser","/api/register/register/getImageVerifyCode","/api/register/register/verifyImageCode","/api/register/register/verifyTextCode","/api/register/register/register"};

    private static final String utoken="eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoidXNlciJ9.G_19cbqgudrLlTP7hBeYOBCt4NXCRCmnx3S2MRCnDnBiJ8JGN7UWgvOY4GcyHXiDApzZanoRzPgNISsfWCobTxUhSPACN_KcqbnlFwzb4xLFJE_AM3OHc_8DUjJo14wI84XKVBCxfS_OZFOV54nuH_EBLIaKA1m4j3CqLMmHedUEC60q05rpirIEO_y3uhJUMcNROzHEzqJOps5FIJ1pl117AQuHCI08sd9O4Iy6TBkGogM05FJV9wflZPqPRlOK6mk3B7fxrz31H_B4dOIh0L8AQG6dTX4n7dor_gfmFEpOSU_yM2eC4q0SjMufczjwZBL6njwTKeiEvPEct_l1Kw";
    @Autowired
    AuthService authService;

    //过虑器的类型
    @Override
    public String filterType() {
        return "pre";
    }

    //过虑器序号，越小越被优先执行
    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        for (String url : LOGIN_URI) {
            if (url.equals(request.getRequestURI())) {
                requestContext.addZuulRequestHeader("Authorization","Bearer "+utoken);
                return false;
            }
        }
        return true;
    }

    //过虑器的内容
    //测试的需求：过虑所有请求，判断头部信息是否有Authorization，如果没有则拒绝访问，否则转发到微服务。
    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        //得到request
        HttpServletRequest request = requestContext.getRequest();
        //得到response
        HttpServletResponse response = requestContext.getResponse();
        //取cookie中的身份令牌
        String tokenFromCookie = authService.getTokenFromCookie(request);
        if(StringUtils.isEmpty(tokenFromCookie)){
            //拒绝访问
            access_denied();
            return null;
        }
        //从redis取出jwt的过期时间
        long expire = authService.getExpire(tokenFromCookie);
        if(expire<0){
            //拒绝访问
            access_denied();
            return null;
        }
        String jwt=authService.getJwt(tokenFromCookie);
        if (jwt==null){
            access_denied();
            return null;
        }
        if(expire<300){
            authService.reset(tokenFromCookie);
        }
        requestContext.addZuulRequestHeader("Authorization","Bearer "+jwt);
        return null;
    }


    //拒绝访问
    private void access_denied(){
        RequestContext requestContext = RequestContext.getCurrentContext();
        //得到response
        HttpServletResponse response = requestContext.getResponse();
        //拒绝访问
        requestContext.setSendZuulResponse(false);
        //设置响应代码
        requestContext.setResponseStatusCode(200);
        //构建响应的信息
        Result result = Result.error(ErrorEnum.UNAUTHENTICATED);
        //转成json
        String jsonString = JSON.toJSONString(result);
        requestContext.setResponseBody(jsonString);
        //转成json，设置contentType
        response.setContentType("application/json;charset=utf-8");
    }

}
