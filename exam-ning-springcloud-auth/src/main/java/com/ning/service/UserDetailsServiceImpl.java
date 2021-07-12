package com.ning.service;

import cn.hutool.core.util.ObjectUtil;
import com.ning.api.user.RemoteUserService;
import com.ning.exception.BaseException;
import com.ning.model.LoginUser;
import com.ning.model.Result;
import com.ning.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    RemoteUserService remoteUserService;

    @Override
    public UserDetails loadUserByUsername(String s) {
        Result<User> userResult = remoteUserService.selectUserByUsername(s);
        checkUser(userResult, s);
        return getUserDetails(userResult);
    }

    public void checkUser(Result<User> userResult, String username) {
        if (ObjectUtil.isNull(userResult) || ObjectUtil.isNull(userResult.getData())) {
            log.info("登录用户：{} 不存在.", username);
            throw new BaseException("登录用户：" + username + " 不存在");
        } else if (userResult.getData().getIsDelete() == 1) {
            log.info("登录用户：{} 已被删除.", username);
            throw new BaseException("对不起，您的账号：" + username + " 已被删除");
        }
    }

    private UserDetails getUserDetails(Result<User> result) {
        User user = result.getData();
        Set<String> dbAuthsSet = new HashSet<>();
        // 获取角色
        dbAuthsSet.addAll(ObjectUtil.isNotEmpty(user.getRoles()) ? user.getRoles() : new HashSet<>());

        // 获取权限
        dbAuthsSet.addAll(ObjectUtil.isNotEmpty(user.getPermissions()) ? user.getPermissions() : new HashSet<>());

        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(dbAuthsSet.toArray(new String[0]));
        return new LoginUser((long) user.getId(), user.getUsername(), user.getPassword(), true, true, true, true, authorities);
    }

}
