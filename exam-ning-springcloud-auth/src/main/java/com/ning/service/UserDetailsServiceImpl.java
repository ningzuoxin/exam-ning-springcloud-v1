package com.ning.service;

import com.ning.api.user.RemoteUserService;
import com.ning.constant.ErrorCodeEnum;
import com.ning.exception.BusinessException;
import com.ning.infrastructure.common.model.CurrentUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final RemoteUserService remoteUserService;

    @Override
    public UserDetails loadUserByUsername(String s) {
        CurrentUser currentUser = remoteUserService.currentUser(s);
        if (Objects.isNull(currentUser)) {
            log.info("current login user not exist. username: {}.", s);
            throw new BusinessException(ErrorCodeEnum.USER_NOT_EXISTS);
        }
        return currentUser;
    }

}
