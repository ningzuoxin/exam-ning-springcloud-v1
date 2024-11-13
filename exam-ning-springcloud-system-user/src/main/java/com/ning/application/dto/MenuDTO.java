package com.ning.application.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 菜单 DTO
 *
 * @author zuoxin.ning
 * @since 2024-11-13 20:30
 */
@Getter
@Setter
public class MenuDTO {

    // 菜单 ID
    private Long id;
    // 菜单名称
    private String menuName;
    // 父菜单 ID
    private Long parentId;
    // 排序
    private Integer sortNum;
    // 路由地址
    private String path;
    // 组件路径
    private String component;
    // 是否为外链，0：否；1：是；
    private Integer isFrame;
    // 菜单类型，1：目录；2：菜单；3：按钮；
    private Integer menuType;
    // 菜单状态，0：正常；1：停用；
    private Integer status;
    // 权限标识
    private String perms;
    // 菜单图标
    private String icon;

}
