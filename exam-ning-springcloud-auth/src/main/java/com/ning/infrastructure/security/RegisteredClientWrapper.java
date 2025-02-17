package com.ning.infrastructure.security;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.Data;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.ConfigurationSettingNames;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.io.Serial;
import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class RegisteredClientWrapper implements Serializable {

    @Serial
    private static final long serialVersionUID = -5849624491334497916L;

    private String id;
    private String clientId;
    private Instant clientIdIssuedAt;
    private String clientSecret;
    private Instant clientSecretExpiresAt;
    private String clientName;
    private Set<String> clientAuthenticationMethods;
    private Set<String> authorizationGrantTypes;
    private Set<String> redirectUris;
    private Set<String> postLogoutRedirectUris;
    private Set<String> scopes;
    private Map<String, Object> clientSettings;
    private Map<String, Object> tokenSettings;

    public static RegisteredClientWrapper from(RegisteredClient registeredClient) {
        RegisteredClientWrapper wrapper = new RegisteredClientWrapper();
        wrapper.setId(registeredClient.getId());
        wrapper.setClientId(registeredClient.getClientId());
        wrapper.setClientIdIssuedAt(registeredClient.getClientIdIssuedAt());
        wrapper.setClientSecret(registeredClient.getClientSecret());
        wrapper.setClientSecretExpiresAt(registeredClient.getClientSecretExpiresAt());
        wrapper.setClientName(registeredClient.getClientName());
        wrapper.setClientAuthenticationMethods(registeredClient.getClientAuthenticationMethods().stream().map(ClientAuthenticationMethod::getValue).collect(Collectors.toSet()));
        wrapper.setAuthorizationGrantTypes(registeredClient.getAuthorizationGrantTypes().stream().map(AuthorizationGrantType::getValue).collect(Collectors.toSet()));
        wrapper.setRedirectUris(registeredClient.getRedirectUris());
        wrapper.setPostLogoutRedirectUris(registeredClient.getPostLogoutRedirectUris());
        wrapper.setScopes(registeredClient.getScopes());
        wrapper.setClientSettings(registeredClient.getClientSettings().getSettings());
        wrapper.setTokenSettings(registeredClient.getTokenSettings().getSettings());
        return wrapper;
    }

    public static RegisteredClient toRegisteredClient(RegisteredClientWrapper wrapper) {
        RegisteredClient.Builder builder = RegisteredClient.withId(wrapper.getId())
                .clientId(wrapper.getClientId())
                .clientSecret(wrapper.getClientSecret())
                .clientIdIssuedAt(wrapper.getClientIdIssuedAt())
                .clientSecretExpiresAt(wrapper.getClientSecretExpiresAt())
                .clientName(wrapper.getClientName());

        wrapper.getClientAuthenticationMethods().forEach(method ->
                builder.clientAuthenticationMethod(new ClientAuthenticationMethod(method)));
        wrapper.getAuthorizationGrantTypes().forEach(grantType ->
                builder.authorizationGrantType(new AuthorizationGrantType(grantType)));

        wrapper.getRedirectUris().forEach(builder::redirectUri);
        wrapper.getPostLogoutRedirectUris().forEach(builder::postLogoutRedirectUri);
        wrapper.getScopes().forEach(builder::scope);
        builder.clientSettings(ClientSettings.withSettings(wrapper.clientSettings).build());

        Map<String, Object> tokenSettingsMap = wrapper.getTokenSettings();
        TokenSettings.Builder tokenSettingsBuilder = TokenSettings.withSettings(tokenSettingsMap);
        if (tokenSettingsMap.containsKey(ConfigurationSettingNames.Token.ACCESS_TOKEN_FORMAT)) {
            Object tokenFormat = tokenSettingsMap.get(ConfigurationSettingNames.Token.ACCESS_TOKEN_FORMAT);
            if (tokenFormat instanceof JSONObject jsonObject) {
                tokenSettingsBuilder.accessTokenFormat(new OAuth2TokenFormat(jsonObject.getStr("value")));
            }
        } else {
            tokenSettingsBuilder.accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED);
        }

        tokenSettingsBuilder.accessTokenTimeToLive(Duration.parse(tokenSettingsMap.get(ConfigurationSettingNames.Token.ACCESS_TOKEN_TIME_TO_LIVE).toString()));
        tokenSettingsBuilder.authorizationCodeTimeToLive(Duration.parse(tokenSettingsMap.get(ConfigurationSettingNames.Token.AUTHORIZATION_CODE_TIME_TO_LIVE).toString()));
        tokenSettingsBuilder.deviceCodeTimeToLive(Duration.parse(tokenSettingsMap.get(ConfigurationSettingNames.Token.DEVICE_CODE_TIME_TO_LIVE).toString()));
        tokenSettingsBuilder.refreshTokenTimeToLive(Duration.parse(tokenSettingsMap.get(ConfigurationSettingNames.Token.REFRESH_TOKEN_TIME_TO_LIVE).toString()));

        builder.tokenSettings(tokenSettingsBuilder.build());

        return builder.build();
    }

    public static String toJsonStr(RegisteredClient client) {
        return JSONUtil.toJsonStr(from(client));
    }

    public static RegisteredClient fromJsonStr(String jsonStr) {
        RegisteredClientWrapper wrapper = JSONUtil.toBean(jsonStr, RegisteredClientWrapper.class);
        return toRegisteredClient(wrapper);
    }

}
