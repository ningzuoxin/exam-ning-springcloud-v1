package com.ning.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ning.entity.Role;
import com.ning.entity.RoleMenu;
import com.ning.manager.RoleManager;
import com.ning.manager.RoleMenuManager;
import com.ning.manager.UserRoleManager;
import com.ning.model.Result;
import com.ning.utils.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class RoleService {

    @Resource
    RoleManager roleManager;
    @Resource
    RoleMenuManager roleMenuManager;
    @Resource
    UserRoleManager userRoleManager;

    /**
     * 分页查询角色列表
     *
     * @param keyword
     * @param pNum
     * @param pSize
     * @return
     */
    public Result selectPage(String keyword, Integer pNum, Integer pSize) {
        return Result.ok(roleManager.selectPage(keyword, pNum, pSize));
    }

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    public Result add(Role role, List<Long> menuIds) {
        long count = roleManager.selectCount(role.getRoleKey());
        if (count > 0) {
            return Result.fail("角色代码已经存在");
        }

        Integer result = roleManager.insert(role);
        if (result == 1) {
            for (Long menuId : menuIds) {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(role.getRoleId());
                roleMenu.setMenuId(menuId);
                roleMenuManager.insert(roleMenu);
            }
            return Result.ok(role);
        } else {
            return Result.fail("添加失败");
        }
    }

    /**
     * 查询角色
     *
     * @param id
     * @return
     */
    public Result get(Long id) {
        Role role = roleManager.selectById(id);
        if (ObjectUtil.isEmpty(role) || "2".equals(role.getDelFlag())) {
            return Result.fail("不存在的角色");
        }

        role.setOwnedMenuIds(roleMenuManager.listOwnedMenuIdByRoleId(id));
        return Result.ok(role);
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    public Result delete(Long id) {
        Role role = roleManager.selectById(id);
        if (ObjectUtil.isEmpty(role) || "2".equals(role.getDelFlag())) {
            return Result.fail("不存在的角色");
        }

        long count = userRoleManager.countByRoleId(id);
        if (count > 0) {
            return Result.fail("存在拥有该角色的用户，不能删除该角色");
        }

        role.setDelFlag("2");  // 删除标志（0代表存在 2代表删除）
        role.setUpdateTime(LocalDateTime.now());
        role.setUpdateBy(SecurityUtils.getLoginUser().getUserId() + "");

        Integer result = roleManager.updateById(role);
        if (result == 1) {
            return Result.ok();
        } else {
            return Result.fail("删除失败");
        }
    }

    /**
     * 编辑角色
     *
     * @param role
     * @param menuIds
     * @return
     */
    @Transactional
    public Result update(Role role, List<Long> menuIds) {
        Role recRole = roleManager.selectById(role.getRoleId());
        if (ObjectUtil.isEmpty(recRole) || "2".equals(recRole.getDelFlag())) {
            return Result.fail("不存在的角色");
        }

        BeanUtil.copyProperties(role, recRole, "roleId", "dataScope", "createBy", "createTime");

        recRole.setUpdateTime(LocalDateTime.now());
        role.setUpdateBy(SecurityUtils.getLoginUser().getUserId() + "");
        Integer result = roleManager.updateById(role);
        if (result == 1) {
            roleMenuManager.deleteByRoleId(role.getRoleId());

            for (Long menuId : menuIds) {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(role.getRoleId());
                roleMenu.setMenuId(menuId);
                roleMenuManager.insert(roleMenu);
            }
            return Result.ok();
        } else {
            return Result.fail("编辑失败");
        }
    }

    /**
     * 查询所有角色
     *
     * @return
     */
    public Result list() {
        return Result.ok(roleManager.listAllRole());
    }
}
