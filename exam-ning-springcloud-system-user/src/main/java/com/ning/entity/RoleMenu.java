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
 * 角色和菜单关联表
 * </p>
 *
 * @author ningning
 * @since 2021-06-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("role_menu")
@ApiModel(value = "RoleMenu对象", description = "角色和菜单关联表")
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色ID")
    @TableId("role_id")
    private Long roleId;

    @ApiModelProperty(value = "菜单ID")
    @TableField("menu_id")
    private Long menuId;

}
