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
import java.util.List;

@Component
public class RoleManager {

    @Resource
    RoleDao roleDao;

    /**
     * 分页查询角色列表
     *
     * @param keyword
     * @param pNum
     * @param pSize
     * @return
     */
    public IPage<Role> selectPage(String keyword, Integer pNum, Integer pSize) {
        // 分页对象
        IPage<Role> iPage = new Page<>(pNum, pSize);

        // 查询对象
        LambdaQueryWrapper<Role> wrapper = new QueryWrapper<Role>().lambda();
        wrapper.eq(Role::getDelFlag, 0);
        if (StrUtil.isNotEmpty(keyword)) {
            wrapper.like(Role::getRoleName, keyword);
        }
        wrapper.orderByDesc(Role::getUpdateTime);
        wrapper.orderByAsc(Role::getRoleSort);

        return roleDao.selectPage(iPage, wrapper);
    }

    /**
     * 根据角色代码统计
     *
     * @param roleKey
     * @return
     */
    public Long selectCount(String roleKey) {
        LambdaQueryWrapper<Role> wrapper = new QueryWrapper<Role>().lambda();
        wrapper.eq(Role::getDelFlag, 0);
        wrapper.eq(Role::getRoleKey, roleKey);

        return roleDao.selectCount(wrapper);
    }

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    public Integer insert(Role role) {
        return roleDao.insert(role);
    }

    /**
     * 查询角色
     *
     * @param id
     * @return
     */
    public Role selectById(Long id) {
        return roleDao.selectById(id);
    }

    /**
     * 修改角色
     *
     * @param role
     * @return
     */
    public Integer updateById(Role role) {
        return roleDao.updateById(role);
    }

    /**
     * 查询所有角色
     *
     * @return
     */
    public List<Role> listAllRole() {
        // 查询对象
        LambdaQueryWrapper<Role> wrapper = new QueryWrapper<Role>().lambda();
        wrapper.eq(Role::getDelFlag, 0);
        return roleDao.selectList(wrapper);
    }

    /**
     * 根据用户id查询角色代码
     *
     * @param userId
     * @return
     */
    public String getRoleKeyByUserId(Long userId) {
        return roleDao.getRoleKeyByUserId(userId);
    }

}
