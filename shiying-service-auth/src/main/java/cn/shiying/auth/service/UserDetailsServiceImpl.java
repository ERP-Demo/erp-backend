package cn.shiying.auth.service;

import cn.shiying.auth.client.DepartmentClient;
import cn.shiying.auth.client.UserClient;
import cn.shiying.common.dto.Result;
import cn.shiying.common.entity.sys.SysMenu;
import cn.shiying.common.entity.sys.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserClient userClient;

    @Autowired
    ClientDetailsService clientDetailsService;

    @Autowired
    DepartmentClient departmentClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //取出身份，如果身份为空说明没有认证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //没有认证统一采用httpbasic认证，httpbasic中存储了client_id和client_secret，开始认证client_id和client_secret
        if(authentication==null){
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(username);
            if(clientDetails!=null){
                //密码
                String clientSecret = clientDetails.getClientSecret();
                return new User(username,clientSecret,AuthorityUtils.commaSeparatedStringToAuthorityList(""));
            }
        }
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        //远程调用用户中心根据账号查询用户信息
        SysUser user = userClient.selectByUsername(username);
        if(user == null){
            return null;
        }

        //取出正确密码（hash值）
        String password = user.getPassword();
        //这里暂时使用静态密码
//       String password ="123";
        //用户权限，这里暂时使用静态数据，最终会从数据库读取
        //从数据库获取权限
        List<String> user_permission = user.getPerms();
        if(user_permission == null){
            user_permission = new ArrayList<>();
        }
        List<Integer> user_role=user.getRoleIdList();
        if(user_role == null){
            user_role = new ArrayList<>();
        }
        List<String> list=new ArrayList<>();
        for (Integer integer : user_role) {
            list.add("ROLE_"+integer);
        }
        String user_permission_string  = StringUtils.join(user_permission.toArray(), ",")+","+StringUtils.join(list.toArray(), ",");
        UserJwt userDetails = new UserJwt(username,
                password,
                AuthorityUtils.commaSeparatedStringToAuthorityList(user_permission_string));
        userDetails.setUser_id(user.getUserId());
        Result result=departmentClient.list(user.getUserId());
        userDetails.setDepartmentId((List<Integer>) result.get("list"));
        return userDetails;
    }
}
