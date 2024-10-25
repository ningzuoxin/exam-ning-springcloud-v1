package com.ning.application.service;

import com.ning.application.assembler.UserAssembler;
import com.ning.application.dto.UserDTO;
import com.ning.constant.BusinessCodeEnum;
import com.ning.domain.entity.User;
import com.ning.domain.repository.UserRepository;
import com.ning.domain.types.Username;
import com.ning.exception.BusinessException;
import com.ning.infrastructure.common.model.PageWrapper;
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

    /**
     * 分页查询用户列表
     *
     * @param keyword  关键词
     * @param pageNum  当前页
     * @param pageSize 当前页条数
     * @return 用户列表
     */
    public PageWrapper<UserDTO> findByPage(String keyword, Integer pageNum, Integer pageSize) {
        PageWrapper<User> pageUserList = userRepository.findByPage(keyword, pageNum, pageSize);
        return userAssembler.toDTOPageList(pageUserList);
    }

    /**
     * 添加用户
     *
     * @param userDTO 用户
     * @return 用户
     */
    public UserDTO add(UserDTO userDTO) {
        Username username = new Username(userDTO.getUsername());
        long count = userRepository.count(username);
        if (count > 0) {
            throw new BusinessException(BusinessCodeEnum.USER_USERNAME_EXISTS);
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword("123456");
        user.setSalt("123456");
        user.setEmail(userDTO.getEmail());
        user.setNickname(userDTO.getNickname());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setGender(userDTO.getGender());

        user = userRepository.save(user);
        return userAssembler.toDTO(user);
    }

}
