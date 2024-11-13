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

/**
 * <p>
 * 系统菜单表
 * </p>
 *
 * @author zuoxin.ning
 * @since 2024-11-13 08:00
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_menu")
@ApiModel(value = "MenuDO对象", description = "系统菜单表")
public class MenuDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "业务ID")
    @TableField("uid")
    private Long uid;

    @ApiModelProperty(value = "菜单名称")
    @TableField("menu_name")
    private String menuName;

    @ApiModelProperty(value = "父菜单ID")
    @TableField("parent_uid")
    private Long parentUid;

    @ApiModelProperty(value = "排序")
    @TableField("sort_num")
    private Integer sortNum;

    @ApiModelProperty(value = "路由地址")
    @TableField("path")
    private String path;

    @ApiModelProperty(value = "组件路径")
    @TableField("component")
    private String component;

    @ApiModelProperty(value = "是否为外链，0：否；1：是；")
    @TableField("is_frame")
    private Integer isFrame;

    @ApiModelProperty(value = "菜单类型，1：目录；2：菜单；3：按钮；")
    @TableField("menu_type")
    private String menuType;

    @ApiModelProperty(value = "菜单状态，0：正常；1：停用；")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "权限标识")
    @TableField("perms")
    private String perms;

    @ApiModelProperty(value = "菜单图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "是否删除，0：未删除；1：已删除；")
    @TableField("is_deleted")
    private Integer isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Instant createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private Instant updateTime;

}
