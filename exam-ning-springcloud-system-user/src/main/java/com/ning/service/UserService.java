package com.ning.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ning.entity.User;
import com.ning.entity.UserRole;
import com.ning.manager.MenuManager;
import com.ning.manager.RoleManager;
import com.ning.manager.UserManager;
import com.ning.manager.UserRoleManager;
import com.ning.model.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("ALL")
@Service
public class UserService {

    @Resource
    UserManager userManager;
    @Resource
    UserRoleManager userRoleManager;
    @Resource
    RoleManager roleManager;
    @Resource
    MenuManager menuManager;

    /**
     * 根据账号查询用户信息
     *
     * @param username
     * @return
     */
    public Result<User> selectUserByUsername(String username) {
        User user = userManager.selectUserByUsername(username);
        if (ObjectUtil.isNotEmpty(user)) {
            // 角色集合
            Set<String> roles = new HashSet<>();
            String role = roleManager.getRoleKeyByUserId((long) user.getId());
            roles.add(StrUtil.isNotEmpty(role) ? role : "");

            // 权限集合
            Set<String> permissions = new HashSet<>();
            permissions.addAll(menuManager.listPermissionsByUserId((long) user.getId()));

            user.setRoles(roles);
            user.setPermissions(permissions);
        }
        return Result.ok(user);
    }

    /**
     * 查询用户列表
     *
     * @return
     */
    public Result<List<User>> selectUsers() {
        return Result.ok(userManager.selectUsers());
    }

    /**
     * 分页查询用户列表
     *
     * @param keyword
     * @param pNum
     * @param pSize
     * @return
     */
    public Result<IPage<User>> selectUserPage(String keyword, Integer pNum, Integer pSize) {
        return Result.ok(userManager.selectUserPage(keyword, pNum, pSize));
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @Transactional
    public Result addUser(User user) {
        long count = userManager.count(user.getUsername());
        if (count > 0) {
            return Result.fail("账号已经存在");
        }

        Integer result = userManager.add(user);
        if (result == 1) {
            UserRole userRole = new UserRole();
            userRole.setUserId(Convert.toLong(user.getId()));
            userRole.setRoleId(user.getRoleId());
            userRoleManager.insert(userRole);
            return Result.ok(user);
        } else {
            return Result.fail("添加失败");
        }
    }

    /**
     * 修改用户
     *
     * @return
     */
    public Result update(User user) {
        User recUser = userManager.getUserById(user.getId());
        if (ObjectUtil.isEmpty(recUser)) {
            return Result.fail("不存在的用户");
        }
        if (recUser.getIsDelete() == 1) {
            return Result.fail("用户已被删除");
        }

        BeanUtil.copyProperties(user, recUser, "id", "salt", "isDelete", "createTime", "username");
        recUser.setUpdateTime((int) DateUtil.currentSeconds());
        Integer result = userManager.edit(recUser);
        if (result == 1) {
            UserRole userRole = userRoleManager.selectOne(Convert.toLong(user.getId()));
            if (ObjectUtil.isNotEmpty(userRole)) {
                userRole.setRoleId(user.getRoleId());
                userRoleManager.updateById(userRole);
            } else {
                userRole = new UserRole();
                userRole.setUserId(Convert.toLong(user.getId()));
                userRole.setRoleId(user.getRoleId());
                userRoleManager.insert(userRole);
            }
            return Result.ok(recUser);
        } else {
            return Result.fail("编辑失败");
        }
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    public Result delete(Integer id) {
        User user = userManager.getUserById(id);
        if (ObjectUtil.isEmpty(user)) {
            return Result.fail("不存在的用户");
        }
        if (user.getIsDelete() == 1) {
            return Result.fail("用户已被删除");
        }

        user.setIsDelete(1);
        user.setUpdateTime((int) DateUtil.currentSeconds());
        Integer result = userManager.edit(user);
        if (result == 1) {
            return Result.ok();
        } else {
            return Result.fail("删除失败");
        }
    }

    /**
     * 获取单个用户
     *
     * @param id
     * @return
     */
    public Result get(Integer id) {
        User user = userManager.getUserById(id);
        if (ObjectUtil.isEmpty(user)) {
            return Result.fail("不存在的用户");
        }
        if (user.getIsDelete() == 1) {
            return Result.fail("用户已被删除");
        }

        UserRole userRole = userRoleManager.selectOne(Convert.toLong(id));
        if (ObjectUtil.isEmpty(userRole)) {
            user.setRoleId(0L);
        } else {
            user.setRoleId(userRole.getRoleId());
        }


        return Result.ok(user);
    }
}
