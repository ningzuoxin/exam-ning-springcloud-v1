package com.ning.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ning.entity.User;
import com.ning.manager.UserManager;
import com.ning.model.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    UserManager userManager;

    /**
     * 根据账号查询用户信息
     *
     * @param username
     * @return
     */
    public Result<User> selectUserByUsername(String username) {
        return Result.ok(userManager.selectUserByUsername(username));
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
    public Result addUser(User user) {
        Integer count = userManager.count(user.getUsername());
        if (count > 0) {
            return Result.fail("账号已经存在");
        }

        Integer result = userManager.add(user);
        if (result == 1) {
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
    public Result editUser(User user) {
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
    public Result deleteUser(Integer id) {
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

}
