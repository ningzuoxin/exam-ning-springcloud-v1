package com.ning.service;

import cn.hutool.core.util.ObjectUtil;
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
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    public Result login(String username, String password) {
        User user = userManager.findUser(username, password);
        if (ObjectUtil.isEmpty(user)) {
            return Result.fail("用户名或密码错误");
        }
        return Result.ok(user);
    }

}
