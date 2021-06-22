package com.ning.manager;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ning.dao.RoleMenuDao;
import com.ning.entity.RoleMenu;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleMenuManager {

    @Resource
    RoleMenuDao roleMenuDao;

    public Integer insert(RoleMenu roleMenu) {
        return roleMenuDao.insert(roleMenu);
    }

    /**
     * 查询角色拥有的菜单id列表
     *
     * @param roleId
     * @return
     */
    public List<Long> listOwnedMenuIdByRoleId(Long roleId) {
        // 查询对象
        LambdaQueryWrapper<RoleMenu> wrapper = new QueryWrapper<RoleMenu>().lambda();
        wrapper.eq(RoleMenu::getRoleId, roleId);
        return roleMenuDao.selectList(wrapper).stream().map(t -> t.getMenuId()).collect(Collectors.toList());
    }

    /**
     * 根据角色id删除角色菜单关联记录
     *
     * @param roleId
     * @return
     */
    public Integer deleteByRoleId(Long roleId) {
        // 查询对象
        LambdaQueryWrapper<RoleMenu> wrapper = new QueryWrapper<RoleMenu>().lambda();
        wrapper.eq(RoleMenu::getRoleId, roleId);
        return roleMenuDao.delete(wrapper);
    }

}
