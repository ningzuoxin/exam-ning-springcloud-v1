package com.ning.infrastructure.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

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
public class UserDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键自增ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "业务ID")
    @TableField("uid")
    private Long uid;

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

    @Schema(description = "是否删除，0：未删除；1：已删除；")
    @TableField("is_deleted")
    private Byte isDeleted;

    @Schema(description = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

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
