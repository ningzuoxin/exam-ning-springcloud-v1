package com.ning.config.jwt;

import com.ning.constant.CommonConstants;
import com.ning.infrastructure.common.model.LoginUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

@SuppressWarnings("ALL")
@Configuration
public class JwtConfig {

    @Resource
    JwtAccessTokenConverter jwtAccessTokenConverter;

    @Bean(name = "jwtTokenStore")
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    /**
     * 可以自主选择使用非对称密钥或对称密钥进行加密，此处对称密钥加密。
     *
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter() {
            // 增强 jwt token
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
                DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
                LoginUser user = (LoginUser) authentication.getUserAuthentication().getPrincipal();
                Map<String, Object> additionalInformation = new LinkedHashMap();
                additionalInformation.put(CommonConstants.USER_NAME, authentication.getName());
                additionalInformation.put(CommonConstants.USER_ID, user.getUserId());
                token.setAdditionalInformation(additionalInformation);
                return super.enhance(accessToken, authentication);
            }
        };

        // 对称加密
        converter.setSigningKey(CommonConstants.SIGNING_KEY);
        return converter;
    }

}
