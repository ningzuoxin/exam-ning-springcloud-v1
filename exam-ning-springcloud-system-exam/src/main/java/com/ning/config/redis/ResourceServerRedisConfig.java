package com.ning.config.redis;

import com.ning.security.CommonUserConverter;
import com.ning.security.CustomAccessDeniedHandler;
import com.ning.security.CustomAuthenticationEntryPoint;
import com.ning.security.CustomRestTemplateResponseErrorHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.web.client.RestTemplate;

/**
 * 使用redis授权服务配置时，打开下面的注释。
 */
//@Configuration
//@EnableResourceServer
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerRedisConfig extends ResourceServerConfigurerAdapter {

    @Value("${security.oauth2.resource.token-info-uri}")
    private String tokenInfoUri;

    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new CustomRestTemplateResponseErrorHandler());
        return restTemplate;
    }

    @Bean
    @Primary
    public ResourceServerTokenServices tokenServices() {
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
        UserAuthenticationConverter userTokenConverter = new CommonUserConverter();
        accessTokenConverter.setUserTokenConverter(userTokenConverter);
        remoteTokenServices.setCheckTokenEndpointUrl(tokenInfoUri);
        remoteTokenServices.setClientId(clientId);
        remoteTokenServices.setClientSecret(clientSecret);
        remoteTokenServices.setRestTemplate(restTemplate());
        remoteTokenServices.setAccessTokenConverter(accessTokenConverter);
        return remoteTokenServices;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/exam/**").authenticated(); // 配置exam访问控制，必须认证后才可以访问
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenServices(tokenServices());

        // 处理 Invalid access token 方面异常
        resources.authenticationEntryPoint(new CustomAuthenticationEntryPoint());

        // 处理 access_denied 方面异常
        resources.accessDeniedHandler(new CustomAccessDeniedHandler());
    }
}
