package com.ning.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * 用户 DTO
 *
 * @author zuoxin.ning
 * @since 2024-10-17 09:00
 */
@Getter
@Setter
public class UserDTO {

    // 用户 ID
    private Long id;
    // 用户名
    private String username;
    // 密码
    private String password;
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
    private Long roleId;
    // 角色集合
    private Set<String> roles;
    // 权限集合
    private Set<String> permissions;

}
