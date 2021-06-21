package com.ning.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author ningning
 * @since 2021-06-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("menu")
@ApiModel(value = "Menu对象", description = "菜单权限表")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单ID")
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    @ApiModelProperty(value = "菜单名称")
    @TableField("menu_name")
    private String menuName;

    @ApiModelProperty(value = "父菜单ID")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty(value = "显示顺序")
    @TableField("order_num")
    private Integer orderNum;

    @ApiModelProperty(value = "路由地址")
    @TableField("path")
    private String path;

    @ApiModelProperty(value = "组件路径")
    @TableField("component")
    private String component;

    @ApiModelProperty(value = "是否为外链（0是 1否）")
    @TableField("is_frame")
    private Integer isFrame;

    @ApiModelProperty(value = "菜单类型（M目录 C菜单 F按钮）")
    @TableField("menu_type")
    private String menuType;

    @ApiModelProperty(value = "菜单状态（0显示 1隐藏）")
    @TableField("visible")
    private String visible;

    @ApiModelProperty(value = "菜单状态（0正常 1停用）")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "权限标识")
    @TableField("perms")
    private String perms;

    @ApiModelProperty(value = "菜单图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "创建者")
    @TableField("create_by")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新者")
    @TableField("update_by")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

    @TableField(exist = false)
    private List<Menu> children = new ArrayList<>();

}
