package com.ning.infrastructure.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @author zuoxin.ning
 * @since 2024-03-24 08:00
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(value = "UserDO对象", description = "系统用户表")
public class UserDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "业务ID")
    @TableField("uid")
    private Long uid;

    @ApiModelProperty(value = "用户名")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "盐")
    @TableField("salt")
    private String salt;

    @ApiModelProperty(value = "昵称")
    @TableField("nickname")
    private String nickname;

    @ApiModelProperty(value = "性别，0：未知；1：男；2：女；")
    @TableField("gender")
    private Integer gender;

    @ApiModelProperty(value = "手机号码")
    @TableField("phone_number")
    private String phoneNumber;

    @ApiModelProperty(value = "身份证号")
    @TableField("id_number")
    private String idNumber;

    @ApiModelProperty(value = "电子邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty(value = "是否删除，0：未删除；1：已删除；")
    @TableField("is_deleted")
    private Integer isDeleted;

    @ApiModelProperty(value = "注册时间")
    @TableField("create_time")
    private Instant createTime;

    @ApiModelProperty(value = "最后更新时间")
    @TableField("update_time")
    private Instant updateTime;

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
