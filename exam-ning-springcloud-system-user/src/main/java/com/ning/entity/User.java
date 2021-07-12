package com.ning.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Set;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author ningning
 * @since 2020-09-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
@ApiModel(value = "User对象", description = "用户表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @NotEmpty(message = "账号不能为空")
    @ApiModelProperty(value = "账号")
    @TableField("username")
    private String username;

    @NotEmpty(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @NotEmpty(message = "邮箱不能为空")
    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String email;

    @NotEmpty(message = "手机不能为空")
    @ApiModelProperty(value = "手机")
    @TableField("mobile")
    private String mobile;

    @ApiModelProperty(value = "密码盐")
    @TableField("salt")
    private String salt;

    @NotEmpty(message = "昵称不能为空")
    @ApiModelProperty(value = "昵称")
    @TableField("nickname")
    private String nickname;

    @ApiModelProperty(value = "身份证号")
    @TableField("idcard")
    private String idcard;

    @ApiModelProperty(value = "性别 0 未知 1 男 2 女")
    @TableField("gender")
    private Integer gender;

    @ApiModelProperty(value = "头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty(value = "是否删除 0 未删除 1 已删除")
    @TableField("is_delete")
    private Integer isDelete;

    @ApiModelProperty(value = "注册时间")
    @TableField("create_time")
    private Integer createTime;

    @ApiModelProperty(value = "最后更新时间")
    @TableField("update_time")
    private Integer updateTime;

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
