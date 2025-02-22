package com.ning.infrastructure.config.jwt;

import com.ning.infrastructure.common.security.CustomAccessDeniedHandler;
import com.ning.infrastructure.common.security.CustomAuthenticationEntryPoint;
import com.ning.infrastructure.security.CustomJwtAuthenticationConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 基于 JWT 的资源服务器配置
 *
 * @author zuoxin.ning
 * @since 2025-01-05 22:30
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class ResourceServerByJwtConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    String jwkSetUri;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .anonymous(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/ids/**", "/users/current-user").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer((resourceServer) -> resourceServer
                        .jwt((jwt) -> jwt.decoder(jwtDecoder()).jwtAuthenticationConverter(new CustomJwtAuthenticationConverter()))
                        .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                )
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                        .accessDeniedHandler(new CustomAccessDeniedHandler())
                );
        return http.build();
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(this.jwkSetUri).build();
    }

}
