package com.ning.infrastructure.persistence.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ning.infrastructure.common.model.AbstractDO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @author zuoxin.ning
 * @since 2025-01-01 00:00:01
 */
@Getter
@Setter
@TableName("sys_user")
@Schema(name = "UserDO", description = "系统用户表")
public class UserDO extends AbstractDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "用户名")
    @TableField("username")
    private String username;

    @Schema(description = "密码")
    @TableField("password")
    private String password;

    @Schema(description = "盐")
    @TableField("salt")
    private String salt;

    @Schema(description = "昵称")
    @TableField("nickname")
    private String nickname;

    @Schema(description = "性别，0：未知；1：男；2：女；")
    @TableField("gender")
    private Byte gender;

    @Schema(description = "手机号码")
    @TableField("phone_number")
    private String phoneNumber;

    @Schema(description = "身份证号")
    @TableField("id_number")
    private String idNumber;

    @Schema(description = "电子邮箱")
    @TableField("email")
    private String email;

    @Schema(description = "头像")
    @TableField("avatar")
    private String avatar;

    // 角色id
    @TableField(exist = false)
    private Long roleId;

    // 角色代码
    @TableField(exist = false)
    private Set<String> roles;

    // 权限代码
    @TableField(exist = false)
    private Set<String> permissions;

}
