package com.ning.domain.entity;

import com.ning.domain.enums.MenuStatusEnum;
import com.ning.domain.types.MenuId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 菜单实体
 *
 * @author zuoxin.ning
 * @since 2024-11-13 08:00
 */
@Getter
@Setter
@ToString
public class Menu {

    // 菜单 ID
    private MenuId id;
    // 菜单名称
    private String menuName;
    // 父菜单 ID
    private MenuId parentId;
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

    public Menu(MenuId id, String menuName, MenuId parentId, Integer sortNum, String path, String component, Integer isFrame, Integer menuType, String perms, String icon) {
        this.id = id;
        this.menuName = menuName;
        this.parentId = parentId;
        this.sortNum = sortNum;
        this.path = path;
        this.component = component;
        this.isFrame = isFrame;
        this.menuType = menuType;
        this.perms = perms;
        this.icon = icon;
        this.status = MenuStatusEnum.NORMAL.getValue();
    }
}
