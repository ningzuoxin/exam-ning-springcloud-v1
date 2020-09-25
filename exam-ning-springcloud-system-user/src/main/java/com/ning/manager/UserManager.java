package com.ning.manager;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ning.dao.UserDao;
import com.ning.entity.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class UserManager {

    @Resource
    UserDao userDao;

    /**
     * 根据账号查询用户信息
     *
     * @param username
     * @return
     */
    public User selectUserByUsername(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        wrapper.eq("is_delete", 0);
        return userDao.selectOne(wrapper);
    }

    /**
     * 查询用户列表
     *
     * @return
     */
    public List<User> selectUsers() {
        return userDao.selectList(null);
    }

}
