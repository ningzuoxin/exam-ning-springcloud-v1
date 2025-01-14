package com.ning.service;

import cn.hutool.core.util.ObjectUtil;
import com.ning.api.user.RemoteUserService;
import com.ning.exception.BaseException;
import com.ning.infrastructure.common.model.LoginUser;
import com.ning.infrastructure.common.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Service(value = "userDetailsService")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final RemoteUserService remoteUserService;

    @Override
    public UserDetails loadUserByUsername(String s) {
        User user = remoteUserService.selectUserByUsername(s);
        checkUser(user, s);
        return getUserDetails(user);
    }

    public void checkUser(User user, String username) {
        if (Objects.isNull(user)) {
            log.info("登录用户：{} 不存在.", username);
            throw new BaseException("登录用户：" + username + " 不存在");
        }
    }

    private UserDetails getUserDetails(User user) {
        Set<String> dbAuthsSet = new HashSet<>();
        // 获取角色
        dbAuthsSet.addAll(ObjectUtil.isNotEmpty(user.getRoles()) ? user.getRoles() : new HashSet<>());

        // 获取权限
        dbAuthsSet.addAll(ObjectUtil.isNotEmpty(user.getPermissions()) ? user.getPermissions() : new HashSet<>());

        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(dbAuthsSet.toArray(new String[0]));
        return new LoginUser(user.getId(), user.getUsername(), user.getPassword(), true, true, true, true, authorities);
    }

}
