package com.ning.domain.entity;

import com.ning.domain.types.RoleId;
import com.ning.domain.types.UserId;
import com.ning.domain.types.Username;

import java.util.Set;

/**
 * 用户实体
 *
 * @author zuoxin.ning
 * @since 2024-03-20
 */
public class User {

    private UserId id;
    private Username username;
    private String password;
    private String email;
    private String mobile;
    private String salt;
    private String nickname;
    private String idcard;
    private Integer gender;
    private String avatar;
    private Integer isDelete;
    private Integer createTime;
    private Integer updateTime;
    private RoleId roleId;
    private Set<String> roles;
    private Set<String> permissions;

}
