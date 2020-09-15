package com.ning.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ning.dao.UserDao;
import com.ning.entity.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserManager {

    @Resource
    UserDao userDao;

    /**
     * 根据账号密码查询单个用户
     *
     * @param username
     * @param password
     * @return
     */
    public User findUser(String username, String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        wrapper.eq("password", password);
        wrapper.eq("is_delete", 0);

        User user = userDao.selectOne(wrapper);
        return user;
    }

}
