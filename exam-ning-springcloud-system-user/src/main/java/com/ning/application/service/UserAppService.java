package com.ning.application.service;

import com.ning.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 用户应用服务
 *
 * @author zuoxin.ning
 * @since 2024-10-16 23:00
 */
@Service
@RequiredArgsConstructor
public class UserAppService {

    private final UserRepository userRepository;

}
