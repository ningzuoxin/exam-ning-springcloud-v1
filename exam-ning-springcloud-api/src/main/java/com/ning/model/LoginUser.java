package com.ning.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 登录用户身份权限
 */
public class LoginUser extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    public LoginUser(Long userId, String username, String password, boolean enabled, boolean accountNonExpired,
                     boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
