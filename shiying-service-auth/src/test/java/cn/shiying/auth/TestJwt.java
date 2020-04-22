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
        String jwtString = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1ODc1OTkyNjMsInVzZXJfbmFtZSI6eyJ1aWQiOjEsInVzZXJuYW1lIjoiYWRtaW4ifSwiYXV0aG9yaXRpZXMiOlsiY3VzdG9tZXI6b3JkZXJzOnNhdmUiLCJwYXRpZW50OmRldGFpbGVkOnNhdmUiLCJ0eXBlOmluZm8iLCJzdXBwbGllcjpvcmRlcnM6bGlzdCIsInJlZ2lzdGVyOnJlZ2lzdGVyOmluZm8iLCJkcnVnczpkZXRhaWxlZDpsaXN0IiwicmVnaXN0ZXI6cmVnaXN0ZXI6ZGVsZXRlIiwicGF0aWVudDpkZXRhaWxlZDp1cGRhdGUiLCJ3YXJlaG91c2U6b3JkZXJzOmNoZWNrIiwic3VwcGxpZXI6b3JkZXJzOmluZm8iLCJzeXM6cm9sZTpsaXN0Iiwic3lzOm1lbnU6c2VsZWN0Iiwic3lzOnNjaGVkdWxlOnVwZGF0ZSIsImRlcGFydG1lbnQ6RGVwYXJ0bWVudDpsaXN0IiwicmVnaXN0ZXI6cmVnaXN0ZXI6dXBkYXRlIiwic3lzOnNjaGVkdWxlOnNhdmUiLCJkcnVnczpkZXRhaWxlZDppbmZvIiwicHJvZHVjdDpsaXN0IiwiZGVwYXJ0bWVudDpEZXBhcnRtZW50OmRlbGV0ZSIsInN5czpyb2xlOnNhdmUiLCJkcnVnczpkZXRhaWxlZDp1cGRhdGUiLCJ3YXJlaG91c2U6ZGV0YWxpczpsaXN0Iiwid2FyZWhvdXNlOmVudHJ5Omxpc3QiLCJjdXN0b21lcjpvcmRlcnM6bGlzdCIsInN5czpzY2hlZHVsZTpsb2ciLCJ3YXJlaG91c2U6b3JkZXJzOnNhdmUiLCJ3YXJlaG91c2U6aW5mbyIsInN5czpyb2xlOnVwZGF0ZSIsImN1c3RvbWVyOmN1c3RvbWVyOnNhdmUiLCJzeXM6c2NoZWR1bGU6bGlzdCIsIndhcmVob3VzZTpkZWxldGUiLCJwcm9kdWN0OmluZm8iLCJkZXBhcnRtZW50OkRlcGFydG1lbnQ6c2F2ZSIsInN1cHBsaWVyOm9yZGVyczpzYXZlIiwid2FyZWhvdXNlOmRldGFsaXM6aW5mbyIsInN1cHBsaWVyOmRldGFpbGVkOmluZm8iLCJ0eXBlOmxpc3QiLCJzeXM6dXNlcjpzYXZlIiwicGF0aWVudDpkZXRhaWxlZDpsaXN0Iiwid2FyZWhvdXNlOmVudHJ5OmNoZWNrIiwiY3VzdG9tZXI6b3JkZXJzOnVwZGF0ZSIsInN5czpzY2hlZHVsZTppbmZvIiwic3VwcGxpZXI6b3JkZXJzOnVwZGF0ZSIsInR5cGU6dXBkYXRlIiwic3lzOm1lbnU6ZGVsZXRlIiwic3lzOm1lbnU6dXBkYXRlIiwiUk9MRV8yIiwicHJvZHVjdDp1cGRhdGUiLCJ3YXJlaG91c2U6ZGV0YWxpczpzYXZlIiwidHlwZTpkZWxldGUiLCJjdXN0b21lcjpjdXN0b21lcjp1cGRhdGUiLCJwcm9kdWN0OnNhdmUiLCJzeXM6bWVudTpsaXN0Iiwic3VwcGxpZXI6b3JkZXJzOmRlbGV0ZSIsInByb2R1Y3Q6ZGVsZXRlIiwic3VwcGxpZXI6b3JkZXJzOmNoZWNrIiwiY3VzdG9tZXI6b3JkZXJzOmRlbGV0ZSIsInN1cHBsaWVyOmRldGFpbGVkOmxpc3QiLCJwYXRpZW50OmRldGFpbGVkOmluZm8iLCJzeXM6c2NoZWR1bGU6cmVzdW1lIiwid2FyZWhvdXNlOmRldGFsaXM6ZGVsZXRlIiwid2FyZWhvdXNlOmRldGFsaXM6dXBkYXRlIiwic3VwcGxpZXI6c3RhdHVzOmxpc3QiLCJjdXN0b21lcjpjdXN0b21lcjpsaXN0Iiwic3lzOnVzZXI6ZGVsZXRlIiwiY3VzdG9tZXI6b3JkZXJzOmluZm8iLCJ0eXBlOnNhdmUiLCJyZWdpc3RlcjpyZWdpc3RlcjpzYXZlIiwic3lzOm1lbnU6aW5mbyIsInN5czp1c2VyOnVwZGF0ZSIsInN1cHBsaWVyOmRldGFpbGVkOnVwZGF0ZSIsIndhcmVob3VzZTplbnRyeTp1cGRhdGUiLCJzeXM6cm9sZTpzZWxlY3QiLCJjdXN0b21lcjpjdXN0b21lcjpkZWxldGUiLCJ3YXJlaG91c2U6bGlzdCIsImRydWdzOmRldGFpbGVkOmRlbGV0ZSIsInN1cHBsaWVyOmRldGFpbGVkOmRlbGV0ZSIsInN5czp1c2VyOmxpc3QiLCJzeXM6bWVudTpzYXZlIiwiY3VzdG9tZXI6Y3VzdG9tZXI6aW5mbyIsInN5czpyb2xlOmluZm8iLCJkZXBhcnRtZW50OkRlcGFydG1lbnQ6aW5mbyIsInN5czpzY2hlZHVsZTpkZWxldGUiLCJ3YXJlaG91c2U6dXBkYXRlIiwiZGVwYXJ0bWVudDpEZXBhcnRtZW50OnVwZGF0ZSIsInBhdGllbnQ6ZGV0YWlsZWQ6ZGVsZXRlIiwic3VwcGxpZXI6ZGV0YWlsZWQ6c2F2ZSIsInN5czp1c2VyOmluZm8iLCJkcnVnczpkZXRhaWxlZDpzYXZlIiwic3lzOnNjaGVkdWxlOnJ1biIsInN5czpyb2xlOmRlbGV0ZSIsIndhcmVob3VzZTpzYXZlIiwicmVnaXN0ZXI6cmVnaXN0ZXI6bGlzdCIsInN5czpzY2hlZHVsZTpwYXVzZSJdLCJqdGkiOiIzYzYwODUzNS0xNjkwLTQwZjQtYWU5NS0yMTkxOWFlODBmYzMiLCJjbGllbnRfaWQiOiJTeVdlYkFwcCIsInNjb3BlIjpbImFwcCJdfQ.KmoHL4n6MmgJWk5Djx0qePd_Qc1LusCOyRmrL0TQomS2AwNTnTCA9kQnqFLwGulY7oLS_YOjM3rJ9Vp7HIpXqqZwnlIS7jy0oFewOoOEVk49p8atu35vVbZu_8xIh09_6kSyzGKN4yf91Deoqtgd8TQZGb_heSvxIxBnoSOukB68Ulew9UT4acTpvol3wrl6kej0uRldgmtcKp1JJWIneGVdNRo6qPHGZBRjUFra1Znf5eugwFItbCdgwKTlD3GhjrnBfbPDiazi3CvU6sZCdWVL-v5rvN6FRMiN82GAGHc9jKi1abHTN50fD7OurL9Q2A06oORLepLDgSMdF1TOUA";
        //校验jwt令牌
        Jwt jwt = JwtHelper.decodeAndVerify(jwtString, new RsaVerifier(publickey));
        //拿到jwt令牌中自定义的内容
        String claims = jwt.getClaims();
        System.out.println(claims);
    }
}
