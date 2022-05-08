package com.ning.manager;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    /**
     * 分页查询用户列表
     *
     * @param keyword
     * @param pNum
     * @param pSize
     * @return
     */
    public IPage<User> selectUserPage(String keyword, Integer pNum, Integer pSize) {
        // 分页对象
        IPage<User> iPage = new Page<>(pNum, pSize);

        // 查询对象
        LambdaQueryWrapper<User> wrapper = new QueryWrapper<User>().lambda();
        wrapper.eq(User::getIsDelete, 0);
        if (StrUtil.isNotEmpty(keyword)) {
            wrapper.like(User::getNickname, keyword).or().like(User::getUsername, keyword).or().like(User::getEmail, keyword).or().like(User::getMobile, keyword);
        }
        wrapper.orderByDesc(User::getCreateTime);

        return userDao.selectPage(iPage, wrapper);
    }

    /**
     * 根据账号统计用户数
     *
     * @param username
     * @return
     */
    public Long count(String username) {
        return userDao.selectCount(new QueryWrapper<User>().lambda().eq(User::getIsDelete, 0).eq(User::getUsername, username));
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    public Integer add(User user) {
        return userDao.insert(user);
    }

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    public Integer edit(User user) {
        return userDao.updateById(user);
    }

    /**
     * 根据id查询用户
     *
     * @param id
     * @return
     */
    public User getUserById(Integer id) {
        return userDao.selectById(id);
    }

}
