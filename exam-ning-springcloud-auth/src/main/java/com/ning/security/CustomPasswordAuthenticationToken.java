package com.ning.security;

import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationGrantAuthenticationToken;

import java.util.Map;

@Getter
public class CustomPasswordAuthenticationToken extends OAuth2AuthorizationGrantAuthenticationToken {

    private final String username;
    private final String password;
    private final String clientId;

    public CustomPasswordAuthenticationToken(Authentication clientPrincipal, String username, String password) {
        super(AuthorizationGrantType.PASSWORD, clientPrincipal, Map.of());
        this.password = password;
        this.username = username;
        this.clientId = clientPrincipal.getName();
    }

}
