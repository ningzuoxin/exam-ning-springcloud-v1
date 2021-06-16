package com.ning.manager;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ning.dao.RoleDao;
import com.ning.entity.Role;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RoleManager {

    @Resource
    RoleDao roleDao;

    public IPage<Role> selectPage(String keyword, Integer pNum, Integer pSize) {
        // 分页对象
        IPage<Role> iPage = new Page<>(pNum, pSize);

        // 查询对象
        LambdaQueryWrapper<Role> wrapper = new QueryWrapper<Role>().lambda();
        wrapper.eq(Role::getDelFlag, 0);
        if (StrUtil.isNotEmpty(keyword)) {
            wrapper.like(Role::getRoleName, keyword);
        }
        wrapper.orderByDesc(Role::getRoleSort, Role::getUpdateTime);

        return roleDao.selectPage(iPage, wrapper);
    }

}
