package com.ning.config.jwt;

import com.ning.constant.CommonConstants;
import org.springframework.beans.factory.annotation.Qualifier;
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

import javax.annotation.Resource;

/**
 * OAuth2 认证服务配置
 * 基于JWT
 */
@SuppressWarnings("ALL")
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerJwtConfig extends AuthorizationServerConfigurerAdapter {

    @Resource
    AuthenticationManager authenticationManager;

    @Resource
    @Qualifier(value = "jwtTokenStore")
    TokenStore tokenStore;

    @Resource
    JwtAccessTokenConverter jwtAccessTokenConverter;

    /**
     * 配置令牌端点(Token Endpoint)的安全约束
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
     * 配置客户端详细信息
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 密码模式
        clients.inMemory()
                .withClient(CommonConstants.CLIENT_ID)
                .scopes(CommonConstants.SCOPES)
                .secret(CommonConstants.CLIENT_SECRET)
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
                .tokenStore(tokenStore)
                .tokenEnhancer(jwtAccessTokenConverter)
                .tokenServices(defaultTokenServices());
    }

    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore);
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setTokenEnhancer(jwtAccessTokenConverter);
        tokenServices.setAccessTokenValiditySeconds(3600 * 2);
        return tokenServices;
    }

}
