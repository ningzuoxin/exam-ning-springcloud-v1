package com.ning.application.service;

import com.ning.application.assembler.UserAssembler;
import com.ning.application.dto.UserDTO;
import com.ning.domain.entity.User;
import com.ning.domain.repository.UserRepository;
import com.ning.domain.types.Username;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private final UserAssembler userAssembler = UserAssembler.INSTANCE;

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    public UserDTO getByUsername(String username) {
        User user = userRepository.find(new Username(username));
        return userAssembler.toDTO(user);
    }

    /**
     * 查询全部用户
     *
     * @return 全部用户
     */
    public List<UserDTO> findAll() {
        List<User> userList = userRepository.findAll();
        return userAssembler.toDTOList(userList);
    }

}
