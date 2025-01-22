package com.ning.infrastructure.security;

import com.ning.infrastructure.common.constant.Constants;
import com.ning.domain.entity.CurrentUser;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;
import java.util.Set;

public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        String uid = jwt.getClaimAsString(Constants.JWT_UID);
        String uname = jwt.getClaimAsString(Constants.JWT_SUB);
        List<String> permissions = jwt.getClaimAsStringList(Constants.JWT_PERMISSIONS);

        CurrentUser currentUser = new CurrentUser(Long.parseLong(uid), uname, "N_A", Set.copyOf(permissions));
        return new UsernamePasswordAuthenticationToken(currentUser, currentUser.getPassword(), currentUser.getAuthorities());
    }

}
