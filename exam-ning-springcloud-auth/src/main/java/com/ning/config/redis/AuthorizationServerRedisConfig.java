package com.ning.config.redis;

import com.ning.constant.CommonConstants;
import com.ning.model.LoginUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * OAuth2 认证服务配置
 * 基于Redis存储
 */
//@SuppressWarnings("ALL")
//@Configuration
//@EnableAuthorizationServer
public class AuthorizationServerRedisConfig extends AuthorizationServerConfigurerAdapter {

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private RedisConnectionFactory redisConnectionFactory;
    @Resource
    private UserDetailsService userDetailsService;

    /**
     * 基于Redis实现，令牌保存到缓存
     *
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        tokenStore.setPrefix(CommonConstants.REDIS_STORE_PREFIX);
        return tokenStore;
    }

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
        clients.inMemory()
                .withClient(CommonConstants.CLIENT_ID)
                .scopes(CommonConstants.SCOPES)
                .secret(CommonConstants.CLIENT_SECRET)
                .authorizedGrantTypes("password", "client_credentials", "refresh_token");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                // 指定认证管理器
                .authenticationManager(authenticationManager)
                // 用户账号密码认证
                .userDetailsService(userDetailsService)
                // refresh_token
                .reuseRefreshTokens(false)
                // 指定token存储位置
                .tokenStore(tokenStore())
                // 自定义生成令牌
                .tokenEnhancer(tokenEnhancer());
    }


    /**
     * 增强令牌 添加 user_id 和 user_name 信息
     *
     * @return
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            if (accessToken instanceof DefaultOAuth2AccessToken) {
                DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
                LoginUser user = (LoginUser) authentication.getUserAuthentication().getPrincipal();
                Map<String, Object> additionalInformation = new LinkedHashMap();
                additionalInformation.put(CommonConstants.USER_NAME, authentication.getName());
                additionalInformation.put(CommonConstants.USER_ID, user.getUserId());
                token.setAdditionalInformation(additionalInformation);
            }
            return accessToken;
        };
    }

}
