package com.ning.service;

import com.ning.api.user.RemoteUserService;
import com.ning.model.LoginUser;
import com.ning.model.Result;
import com.ning.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    RemoteUserService remoteUserService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Result<User> userResult = remoteUserService.selectUserByUsername(s);
        return getUserDetails(userResult);
    }

    private UserDetails getUserDetails(Result<User> result) {
        User user = result.getData();
        Set<String> dbAuthsSet = new HashSet<>();
        // 获取角色
        dbAuthsSet.addAll(new HashSet<>());
        // 获取权限
        dbAuthsSet.addAll(new HashSet<>());

        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(dbAuthsSet.toArray(new String[0]));
        return new LoginUser((long) user.getId(), user.getUsername(), user.getPassword(), true, true, true, true, authorities);
    }

}
