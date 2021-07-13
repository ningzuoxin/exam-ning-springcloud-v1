package com.ning.config.redis;

import com.ning.security.CustomAuthenticationEntryPoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 认证服务，提供了用户信息，所以也是资源服务器。
 * 使用redis授权服务配置时，打开下面的注释。
 */
//@Configuration
//@EnableResourceServer
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerRedisConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/oauth/user").authenticated(); // 配置oauth访问控制，必须认证后才可以访问
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        // 处理 Invalid access token 方面异常
        resources.authenticationEntryPoint(new CustomAuthenticationEntryPoint());
    }
}
