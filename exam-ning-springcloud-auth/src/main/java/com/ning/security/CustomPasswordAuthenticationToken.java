package com.ning.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationGrantAuthenticationToken;

import java.util.Map;

public class CustomPasswordAuthenticationToken extends OAuth2AuthorizationGrantAuthenticationToken {

    public CustomPasswordAuthenticationToken(Authentication clientPrincipal, Map<String, Object> additionalParameters) {
        super(AuthorizationGrantType.PASSWORD, clientPrincipal, additionalParameters);
    }

    @Override
    public Object getPrincipal() {
        return this.getAdditionalParameters().get(OAuth2ParameterNames.USERNAME);
    }

    @Override
    public Object getCredentials() {
        return this.getAdditionalParameters().get(OAuth2ParameterNames.PASSWORD);
    }

    public String getClientId() {
        return (String) this.getAdditionalParameters().get(OAuth2ParameterNames.CLIENT_ID);
    }

}
