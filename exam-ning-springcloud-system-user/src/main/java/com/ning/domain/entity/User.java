package com.ning.domain.entity;

import com.ning.domain.types.RoleId;
import com.ning.domain.types.UserId;
import com.ning.domain.types.Username;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * 用户实体
 *
 * @author zuoxin.ning
 * @since 2024-03-20 08:00
 */
@Getter
@Setter
public class User {

    // 用户 ID
    private UserId id;
    // 用户名
    private Username username;
    // 密码
    private String password;
    // 盐
    private String salt;
    // 昵称
    private String nickname;
    // 性别
    private Integer gender;
    // 手机号码
    private String phoneNumber;
    // 身份证号
    private String idNumber;
    // 电子邮箱
    private String email;
    // 头像
    private String avatar;
    // 角色 ID
    private RoleId roleId;
    // 角色集合
    private Set<String> roles;
    // 权限集合
    private Set<String> permissions;

}
