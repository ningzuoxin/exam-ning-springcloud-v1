package com.ning.config.jwt;

import com.ning.constant.CommonConstants;
import com.ning.security.CommonUserConverter;
import com.ning.security.CustomAccessTokenConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.annotation.Resource;

@Configuration
public class JwtConfig {

    @Resource
    JwtAccessTokenConverter jwtAccessTokenConverter;

    @Bean(name = "jwtTokenStore")
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    /**
     * 可以自主选择使用非对称密钥或对称密钥进行加密，此处不设置加密。
     * 必须往容器中注入JwtAccessTokenConverter。
     *
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        CustomAccessTokenConverter accessTokenConverter = new CustomAccessTokenConverter();
        accessTokenConverter.setUserTokenConverter(new CommonUserConverter());
        converter.setAccessTokenConverter(accessTokenConverter);
        // 对称加密
        converter.setSigningKey(CommonConstants.SIGNING_KEY);
        return converter;
    }

}
