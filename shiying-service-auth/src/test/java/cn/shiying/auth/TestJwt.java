package cn.shiying.auth;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @version 1.0
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestJwt {

    //创建jwt令牌
    @Test
    public void testCreateJwt(){
        //密钥库文件
        String keystore = "sy.keystore";
        //密钥库的密码
        String keystore_password = "shiyingkey";

        //密钥库文件路径
        ClassPathResource classPathResource = new ClassPathResource(keystore);
        //密钥别名
        String alias  = "sykey";
        //密钥的访问密码
        String key_password = "shiying";
        //密钥工厂
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(classPathResource,keystore_password.toCharArray());
        //密钥对（公钥和私钥）
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair(alias, key_password.toCharArray());
        //获取私钥
        RSAPrivateKey aPrivate = (RSAPrivateKey) keyPair.getPrivate();
        //jwt令牌的内容
        Map<String,String> body = new HashMap<>();
        body.put("name","itcast");
        String bodyString = JSON.toJSONString(body);
        //生成jwt令牌
        Jwt jwt = JwtHelper.encode(bodyString, new RsaSigner(aPrivate));
        //生成jwt令牌编码
        String encoded = jwt.getEncoded();
        System.out.println(encoded);

    }

    //校验jwt令牌
    @Test
    public void testVerify(){
        //公钥
        String publickey = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjKL/JQQQs6e72rseqfbC8vTzTLKYfrf8PHwQOP8Ic7L78aok6B60rddiQJVR7MyIpegE5xQaZmCvbtIW4+Dn3hbIkfs1smUKZ0ya3V0jtepXHDbiyMdosr+aJJNOAc71G5J2GztebVmUbbvQ/EapbIBK+v/wLEfsrJIz50CixCPSsXEWXjpC3hoZ8xr2wwVulw2XYq3hV1PoiYuvD3SRaDh+stamNoj+JLQO7PeKlnoo4e1kctVhS8fujGG8mkaLvla9u1Y6rFchucoyZRkl28RTcV+rLZ45zNpP0HHceOm/FKgXuhmZbgkm2lJ3mMKWyar5a3d5/iFBcbz5qaADOQIDAQAB-----END PUBLIC KEY-----";
        //jwt令牌
        String jwtString = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiaXRjYXN0In0.M1CA_sA7GqKH_kfzoPRpXw_NXODNiABTDgbZ6RuGsxZK6DDXvII6-RJYJDJbOfLOgKCEf3bCrACNhGdRkuUMcW9N3cosmYmUds6UO7dEICH0BsNIQ0fuXUSk6yFg5sLY0mQvownRLAuCVj5qAPUvpEMcDrKQxsBTog5JbVP27REWjjmEKUq0NWbjkj1HihzBJPWx62xyPlYE_51ga5q_3kdw5Av3_WdZkyVGyunnECjoUht_-WZQ7f0xdKqgDDhLmCIdZSWjKAhPa4gHhD1hDfusF8BR1MIgoueWgak5m5cYu6dISOf5bqckXO6KYA_C87wCrg-EM9WHk-zhsiPodg";
        //校验jwt令牌
        Jwt jwt = JwtHelper.decodeAndVerify(jwtString, new RsaVerifier(publickey));
        //拿到jwt令牌中自定义的内容
        String claims = jwt.getClaims();
        System.out.println(claims);
    }
}
