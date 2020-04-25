package cn.shiying.config;

import cn.shiying.common.entity.token.JwtUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @version 1.0
 **/
@Configuration
public class ResourceServerConfig {

    //公钥
    private static final String PUBLIC_KEY = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjKL/JQQQs6e72rseqfbC8vTzTLKYfrf8PHwQOP8Ic7L78aok6B60rddiQJVR7MyIpegE5xQaZmCvbtIW4+Dn3hbIkfs1smUKZ0ya3V0jtepXHDbiyMdosr+aJJNOAc71G5J2GztebVmUbbvQ/EapbIBK+v/wLEfsrJIz50CixCPSsXEWXjpC3hoZ8xr2wwVulw2XYq3hV1PoiYuvD3SRaDh+stamNoj+JLQO7PeKlnoo4e1kctVhS8fujGG8mkaLvla9u1Y6rFchucoyZRkl28RTcV+rLZ45zNpP0HHceOm/FKgXuhmZbgkm2lJ3mMKWyar5a3d5/iFBcbz5qaADOQIDAQAB-----END PUBLIC KEY-----";

    //定义JwtTokenStore，使用jwt令牌
    @Bean
    public TokenStore tokenStore(JwtAccessTokenConverter jwtAccessTokenConverter) {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    //定义JJwtAccessTokenConverter，使用jwt令牌
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setVerifierKey(getPubKey());
        return converter;
    }
    /**
     * 获取非对称加密公钥 Key
     * @return 公钥 Key
     */
    private String getPubKey() {
        return PUBLIC_KEY;
    }

}
