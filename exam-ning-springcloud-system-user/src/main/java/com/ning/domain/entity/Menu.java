package com.ning.domain.entity;

import cn.hutool.core.collection.CollectionUtil;
import com.ning.domain.enums.MenuStatusEnum;
import com.ning.domain.enums.MenuTypeEnum;
import com.ning.domain.types.MenuId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    // 子菜单
    private List<Menu> children;

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

    /**
     * 树形菜单列表
     *
     * @param menuList 菜单列表
     * @return 菜单列表
     */
    public static List<Menu> tree(List<Menu> menuList) {
        List<Menu> resultList = new ArrayList<>();
        for (Menu menu : menuList) {
            if (menu.getParentId().getValue() == 0) {
                recursionFn(menuList, menu);
                resultList.add(menu);
            }
        }
        if (CollectionUtil.isEmpty(resultList)) {
            resultList = menuList;
        }
        return resultList;
    }

    /**
     * 获取路由名称
     *
     * @return 路由名称
     */
    public String getRouteName() {
        String routerName = StringUtils.capitalize(this.getPath());
        // 非外链并且是一级目录（类型为目录）
        if (isMenuFrame()) {
            routerName = StringUtils.EMPTY;
        }
        return routerName;
    }

    /**
     * 获取路由地址
     *
     * @return 路由地址
     */
    public String getRouterPath() {
        String routerPath = this.getPath();
        // 非外链并且是一级目录（类型为目录）
        if (0 == this.getParentId().getValue() && this.getMenuType() == MenuTypeEnum.Catalog.getValue() && this.getIsFrame() == 1) {
            routerPath = "/" + this.getPath();
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMenuFrame()) {
            routerPath = "/";
        }
        return routerPath;
    }

    /**
     * 获取组件信息
     *
     * @return 组件信息
     */
    public String getRouterComponent() {
        String component = "Layout";
        if (StringUtils.isNotEmpty(this.getComponent()) && !isMenuFrame()) {
            component = this.getComponent();
        }
        return component;
    }

    /**
     * 递归构建
     *
     * @param menuList 菜单列表
     * @param menu     菜单
     */
    private static void recursionFn(List<Menu> menuList, Menu menu) {
        // 得到子节点列表
        List<Menu> childList = getChildList(menuList, menu);
        menu.setChildren(childList);
        for (Menu m : childList) {
            if (hasChild(menuList, m)) {
                // 判断是否有子节点
                for (Menu n : childList) {
                    recursionFn(menuList, n);
                }
            }
        }
    }

    /**
     * 获取子节点菜单列表
     *
     * @param menuList 菜单列表
     * @param menu     菜单
     * @return 菜单列表
     */
    private static List<Menu> getChildList(List<Menu> menuList, Menu menu) {
        List<Menu> resultList = new ArrayList<>();
        for (Menu m : menuList) {
            if (Objects.equals(m.getParentId().getValue(), menu.getId().getValue())) {
                resultList.add(m);
            }
        }
        return resultList;
    }

    /**
     * 判断是否有子节点
     *
     * @param menuList 菜单列表
     * @param menu     菜单
     * @return 是否有子节点
     */
    private static boolean hasChild(List<Menu> menuList, Menu menu) {
        return getChildList(menuList, menu).size() > 0;
    }

    /**
     * 是否为菜单内部跳转
     *
     * @return 是否为菜单内部跳转
     */
    public boolean isMenuFrame() {
        return this.getParentId().getValue() == 0 &&
                this.getMenuType() == MenuTypeEnum.Menu.getValue() &&
                this.getIsFrame() == 1;
    }

}
