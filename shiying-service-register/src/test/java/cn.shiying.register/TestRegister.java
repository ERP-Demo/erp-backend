package cn.shiying.register;

import cn.shiying.common.utils.PageUtils;
import cn.shiying.register.RegisterApplication;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @version 1.0
 **/
@SpringBootTest(classes = { RegisterApplication.class })
@RunWith(SpringRunner.class)
public class TestRegister{

    @Test
    public void test1(){
        System.out.println("数据");

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4GAt6zA8RGNLqRQ6gTYF", "j1LVxtnNaFJaOrlV7BTNT1eGZFmApq");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        request.putQueryParameter("RegionId", "cn-hangzhou");

        request.putQueryParameter("PhoneNumbers","15207416649");
        request.putQueryParameter("SignName","时樱");
        request.putQueryParameter("TemplateCode","SMS_187746853");
        HashMap<String, String> map = new HashMap<>();
        String code = String.valueOf((int)((Math.random()*9+1)*1000));
        map.put("code",code);
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(map));

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
