package com.ning.service;

import com.ning.entity.User;
import com.ning.manager.UserManager;
import com.ning.model.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    UserManager userManager;

    /**
     * 根据账号查询用户信息
     *
     * @param username
     * @return
     */
    public Result<User> selectUserByUsername(String username) {
        return Result.ok(userManager.selectUserByUsername(username));
    }

}