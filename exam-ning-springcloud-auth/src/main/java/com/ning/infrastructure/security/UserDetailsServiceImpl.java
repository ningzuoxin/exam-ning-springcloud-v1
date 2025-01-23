package com.ning.infrastructure.security;

import com.ning.domain.service.CurrentUserService;
import com.ning.infrastructure.common.enums.ErrorCodeEnum;
import com.ning.infrastructure.common.exception.BusinessException;
import com.ning.domain.entity.CurrentUser;
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

    private final CurrentUserService currentUserService;

    @Override
    public UserDetails loadUserByUsername(String s) {
        CurrentUser currentUser = currentUserService.currentUser(s);
        if (Objects.isNull(currentUser)) {
            log.info("current login user not exist. username: {}.", s);
            throw new BusinessException(ErrorCodeEnum.USER_NOT_EXISTS);
        }
        return currentUser;
    }

}
