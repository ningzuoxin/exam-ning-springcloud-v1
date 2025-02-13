package com.ning.infrastructure.security;

import cn.hutool.json.JSONUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;

public class CustomOAuth2AuthorizationService implements OAuth2AuthorizationService {

    private static final String CACHE_KEY_PREFIX = "oauth2:oauth2_authorization:";
    private final RedisTemplate<String, String> redisTemplate;

    public CustomOAuth2AuthorizationService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void save(OAuth2Authorization authorization) {
        String authorizationJson = JSONUtil.toJsonStr(authorization);
        redisTemplate.opsForValue().set(CACHE_KEY_PREFIX + authorization.getId(), authorizationJson);
    }

    @Override
    public void remove(OAuth2Authorization authorization) {
        redisTemplate.delete(CACHE_KEY_PREFIX + authorization.getId());
    }

    @Override
    public OAuth2Authorization findById(String id) {
        String authorizationJson = redisTemplate.opsForValue().get(CACHE_KEY_PREFIX + id);
        if (authorizationJson == null) {
            return null;
        }

        return JSONUtil.toBean(authorizationJson, OAuth2Authorization.class);
    }

    @Override
    public OAuth2Authorization findByToken(String token, OAuth2TokenType tokenType) {
        String authorizationId = redisTemplate.opsForValue().get(CACHE_KEY_PREFIX + tokenType.getValue() + ":" + token);
        if (authorizationId != null) {
            return findById(authorizationId);
        }
        return null;
    }

}
