package com.ning.infrastructure.security;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

public class CustomRegisteredClientRepository implements RegisteredClientRepository {

    private static final String CACHE_KEY_PREFIX = "oauth2:registered_client:";
    private final RedisTemplate<String, String> redisTemplate;

    public CustomRegisteredClientRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void save(RegisteredClient registeredClient) {
        String jsonStr = RegisteredClientWrapper.toJsonStr(registeredClient);
        redisTemplate.opsForValue().set(CACHE_KEY_PREFIX + registeredClient.getId(), jsonStr);
        redisTemplate.opsForValue().set(CACHE_KEY_PREFIX + registeredClient.getClientId(), jsonStr);
    }

    @Override
    public RegisteredClient findById(String id) {
        String jsonStr = redisTemplate.opsForValue().get(CACHE_KEY_PREFIX + id);
        if (jsonStr == null) {
            return null;
        }

        return RegisteredClientWrapper.fromJsonStr(jsonStr);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        String jsonStr = redisTemplate.opsForValue().get(CACHE_KEY_PREFIX + clientId);
        if (jsonStr == null) {
            return null;
        }

        return RegisteredClientWrapper.fromJsonStr(jsonStr);
    }
}
