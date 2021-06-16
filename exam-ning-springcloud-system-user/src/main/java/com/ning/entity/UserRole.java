package com.ning.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户和角色关联表
 * </p>
 *
 * @author ningning
 * @since 2021-06-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_role")
@ApiModel(value = "UserRole对象", description = "用户和角色关联表")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    @TableId("user_id")
    private Long userId;

    @ApiModelProperty(value = "角色ID")
    @TableField("role_id")
    private Long roleId;

}
