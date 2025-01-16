package com.ning.infrastructure.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrentUser implements UserDetails {

    @Serial
    private static final long serialVersionUID = -209974169231119879L;

    private Long userId;
    private String uname;
    private String pwd;
    private String nickname;
    private String avatar;
    private Long roleId;
    private Set<String> roles;
    private Set<String> permissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<String> authoritySet = new HashSet<>();
        // roles
        authoritySet.addAll(Objects.nonNull(roles) ? roles : Set.of());

        // permissions
        authoritySet.addAll(Objects.nonNull(permissions) ? permissions : Set.of());
        return AuthorityUtils.createAuthorityList(authoritySet.toArray(new String[0]));
    }

    @Override
    public String getPassword() {
        return this.pwd;
    }

    @Override
    public String getUsername() {
        return this.uname;
    }

}
