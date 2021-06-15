package com.ning.config.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.annotation.Resource;

/**
 * 授权服务配置 --- 基于JWT
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerJwtConfig extends AuthorizationServerConfigurerAdapter {

    @Resource
    private AuthenticationManager authenticationManager;

    /**
     * token储存策略
     *
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtTokenEnhancer());
    }

    /**
     * 定义令牌端点上的安全性约束
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    /**
     * 用于定义客户端详细信息服务的配置程序，可以初始化客户端详细信息。
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 密码模式
        clients.inMemory()
                .withClient("ning666888")
                .scopes("ningning")
                .secret("888666")
                .authorizedGrantTypes("password", "refresh_token");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                // 指定认证管理器
                .authenticationManager(authenticationManager)
                // refresh_token
                .reuseRefreshTokens(false)
                // 指定token存储位置
                .tokenStore(tokenStore())
                .tokenEnhancer(jwtTokenEnhancer())
                .tokenServices(defaultTokenServices());
    }

    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setTokenEnhancer(jwtTokenEnhancer());
        tokenServices.setAccessTokenValiditySeconds(60 * 10);
        return tokenServices;
    }

    /**
     * 定义jwt的生成方式
     * 可以自主选择使用非对称密钥或对称密钥进行加密，此处不设置加密。
     *
     * @return JwtAccessTokenConverter
     */
    private JwtAccessTokenConverter jwtTokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        // 非对称加密，但jwt长度过长
        // KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("spring-jwt.jks"), "admin123456".toCharArray()).getKeyPair("spring-jwt");
        // converter.setKeyPair(keyPair);

        // 对称加密
        converter.setSigningKey("ning123456");
        return converter;
    }


}
