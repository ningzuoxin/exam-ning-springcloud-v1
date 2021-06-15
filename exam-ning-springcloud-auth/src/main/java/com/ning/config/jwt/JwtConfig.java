package com.ning.config.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

//@Configuration
public class JwtConfig {

    @Bean(name = "jwtTokenStore")
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtTokenEnhancer());
    }

    /**
     * 可以自主选择使用非对称密钥或对称密钥进行加密，此处不设置加密。
     * 必须往容器中注入JwtAccessTokenConverter。
     *
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtTokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        // 要使用非对称密钥时，放开下面注释。
//        Resource resource = new ClassPathResource("public.cert"); // 公钥文件
//        String publicKey;
//        try {
//            publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        // 对称加密
        converter.setSigningKey("ning123456");
        return converter;
    }

}
