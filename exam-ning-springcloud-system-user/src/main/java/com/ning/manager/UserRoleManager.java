package com.ning.manager;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ning.dao.UserRoleDao;
import com.ning.entity.UserRole;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserRoleManager {

    @Resource
    UserRoleDao userRoleDao;

    public Long countByRoleId(Long roleId) {
        // 查询对象
        LambdaQueryWrapper<UserRole> wrapper = new QueryWrapper<UserRole>().lambda();
        wrapper.eq(UserRole::getRoleId, roleId);
        return userRoleDao.selectCount(wrapper);
    }

    public Integer insert(UserRole userRole) {
        return userRoleDao.insert(userRole);
    }

    public UserRole selectOne(Long userId) {
        // 查询对象
        LambdaQueryWrapper<UserRole> wrapper = new QueryWrapper<UserRole>().lambda();
        wrapper.eq(UserRole::getUserId, userId);
        return userRoleDao.selectOne(wrapper);
    }

    public Integer updateById(UserRole userRole) {
        return userRoleDao.updateById(userRole);
    }

}
