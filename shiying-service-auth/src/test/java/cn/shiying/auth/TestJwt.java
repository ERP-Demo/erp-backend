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
        String jwtString = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbImFwcCJdLCJpZCI6MSwiZXhwIjoxNTg3MTU4NTg3LCJhdXRob3JpdGllcyI6WyJjdXN0b21lcjpvcmRlcnM6c2F2ZSIsInBhdGllbnQ6ZGV0YWlsZWQ6c2F2ZSIsInR5cGU6aW5mbyIsInN1cHBsaWVyOm9yZGVyczpsaXN0IiwicmVnaXN0ZXI6cmVnaXN0ZXI6aW5mbyIsImRydWdzOmRldGFpbGVkOmxpc3QiLCJyZWdpc3RlcjpyZWdpc3RlcjpkZWxldGUiLCJwYXRpZW50OmRldGFpbGVkOnVwZGF0ZSIsIndhcmVob3VzZTpvcmRlcnM6Y2hlY2siLCJzdXBwbGllcjpvcmRlcnM6aW5mbyIsInN5czpyb2xlOmxpc3QiLCJzeXM6bWVudTpzZWxlY3QiLCJzeXM6c2NoZWR1bGU6dXBkYXRlIiwiZGVwYXJ0bWVudDpEZXBhcnRtZW50Omxpc3QiLCJyZWdpc3RlcjpyZWdpc3Rlcjp1cGRhdGUiLCJzeXM6c2NoZWR1bGU6c2F2ZSIsImRydWdzOmRldGFpbGVkOmluZm8iLCJwcm9kdWN0Omxpc3QiLCJkZXBhcnRtZW50OkRlcGFydG1lbnQ6ZGVsZXRlIiwic3lzOnJvbGU6c2F2ZSIsImRydWdzOmRldGFpbGVkOnVwZGF0ZSIsIndhcmVob3VzZTpkZXRhbGlzOmxpc3QiLCJ3YXJlaG91c2U6ZW50cnk6bGlzdCIsImN1c3RvbWVyOm9yZGVyczpsaXN0Iiwic3lzOnNjaGVkdWxlOmxvZyIsIndhcmVob3VzZTpvcmRlcnM6c2F2ZSIsIndhcmVob3VzZTppbmZvIiwic3lzOnJvbGU6dXBkYXRlIiwiY3VzdG9tZXI6Y3VzdG9tZXI6c2F2ZSIsInN5czpzY2hlZHVsZTpsaXN0Iiwid2FyZWhvdXNlOmRlbGV0ZSIsInByb2R1Y3Q6aW5mbyIsImRlcGFydG1lbnQ6RGVwYXJ0bWVudDpzYXZlIiwic3VwcGxpZXI6b3JkZXJzOnNhdmUiLCJ3YXJlaG91c2U6ZGV0YWxpczppbmZvIiwic3VwcGxpZXI6ZGV0YWlsZWQ6aW5mbyIsInR5cGU6bGlzdCIsInN5czp1c2VyOnNhdmUiLCJwYXRpZW50OmRldGFpbGVkOmxpc3QiLCJ3YXJlaG91c2U6ZW50cnk6Y2hlY2siLCJjdXN0b21lcjpvcmRlcnM6dXBkYXRlIiwic3lzOnNjaGVkdWxlOmluZm8iLCJzdXBwbGllcjpvcmRlcnM6dXBkYXRlIiwidHlwZTp1cGRhdGUiLCJzeXM6bWVudTpkZWxldGUiLCJzeXM6bWVudTp1cGRhdGUiLCJwcm9kdWN0OnVwZGF0ZSIsIndhcmVob3VzZTpkZXRhbGlzOnNhdmUiLCJ0eXBlOmRlbGV0ZSIsImN1c3RvbWVyOmN1c3RvbWVyOnVwZGF0ZSIsInByb2R1Y3Q6c2F2ZSIsInN5czptZW51Omxpc3QiLCJzdXBwbGllcjpvcmRlcnM6ZGVsZXRlIiwicHJvZHVjdDpkZWxldGUiLCJzdXBwbGllcjpvcmRlcnM6Y2hlY2siLCJjdXN0b21lcjpvcmRlcnM6ZGVsZXRlIiwic3VwcGxpZXI6ZGV0YWlsZWQ6bGlzdCIsInBhdGllbnQ6ZGV0YWlsZWQ6aW5mbyIsInN5czpzY2hlZHVsZTpyZXN1bWUiLCJ3YXJlaG91c2U6ZGV0YWxpczpkZWxldGUiLCJ3YXJlaG91c2U6ZGV0YWxpczp1cGRhdGUiLCJzdXBwbGllcjpzdGF0dXM6bGlzdCIsImN1c3RvbWVyOmN1c3RvbWVyOmxpc3QiLCJzeXM6dXNlcjpkZWxldGUiLCJjdXN0b21lcjpvcmRlcnM6aW5mbyIsInR5cGU6c2F2ZSIsInJlZ2lzdGVyOnJlZ2lzdGVyOnNhdmUiLCJzeXM6bWVudTppbmZvIiwic3lzOnVzZXI6dXBkYXRlIiwic3VwcGxpZXI6ZGV0YWlsZWQ6dXBkYXRlIiwid2FyZWhvdXNlOmVudHJ5OnVwZGF0ZSIsInN5czpyb2xlOnNlbGVjdCIsImN1c3RvbWVyOmN1c3RvbWVyOmRlbGV0ZSIsIndhcmVob3VzZTpsaXN0IiwiZHJ1Z3M6ZGV0YWlsZWQ6ZGVsZXRlIiwic3VwcGxpZXI6ZGV0YWlsZWQ6ZGVsZXRlIiwic3lzOnVzZXI6bGlzdCIsInN5czptZW51OnNhdmUiLCJjdXN0b21lcjpjdXN0b21lcjppbmZvIiwic3lzOnJvbGU6aW5mbyIsImRlcGFydG1lbnQ6RGVwYXJ0bWVudDppbmZvIiwic3lzOnNjaGVkdWxlOmRlbGV0ZSIsIndhcmVob3VzZTp1cGRhdGUiLCJkZXBhcnRtZW50OkRlcGFydG1lbnQ6dXBkYXRlIiwicGF0aWVudDpkZXRhaWxlZDpkZWxldGUiLCJzdXBwbGllcjpkZXRhaWxlZDpzYXZlIiwic3lzOnVzZXI6aW5mbyIsImRydWdzOmRldGFpbGVkOnNhdmUiLCJzeXM6c2NoZWR1bGU6cnVuIiwic3lzOnJvbGU6ZGVsZXRlIiwid2FyZWhvdXNlOnNhdmUiLCJyZWdpc3RlcjpyZWdpc3RlcjpsaXN0Iiwic3lzOnNjaGVkdWxlOnBhdXNlIl0sImp0aSI6ImNhZjJiZDYxLTBiZTItNDIzMi05MDJiLTJlZTQ2YzZmNDk3NSIsImNsaWVudF9pZCI6IlN5V2ViQXBwIn0.B313AHjlBWsn05cFv07u7DmKGF5KIiT0tIMYpmqkSoRcdY00CUUL6-OoL8WxYfANtLVcS1b0Eard7CG5nIN0qugS1eqAJtEzkTZEsRYwxshfwXg5GQzkO6xRBSFexHnlvGnmV9oM53YTXOZpHAbRAxnzo6NT-3W20ly9m0UUfa82ZESreH3EpjlB2ktvmLBdRXqsmJ1Eu4RqPV7SGTvTmoYsIY6pC0DypbiZU-3ipFkjirB-LIcvgxPYjlUQC_xfaONjNG-CTJDFLLVS8LUhIlKisFgb6mt7YYXT6UbWl5fPGSJQvvYJkZMtZkm8d3O_pfKwBTVVuNlLqiOjzdytvw";
        //校验jwt令牌
        Jwt jwt = JwtHelper.decodeAndVerify(jwtString, new RsaVerifier(publickey));
        //拿到jwt令牌中自定义的内容
        String claims = jwt.getClaims();
        System.out.println(claims);
    }
}
