package com.ning.domain.repository;

import com.ning.domain.entity.Menu;
import com.ning.domain.types.MenuId;
import com.ning.infrastructure.common.model.PageWrapper;

import java.util.List;
import java.util.Optional;

/**
 * 菜单仓储
 *
 * @author zuoxin.ning
 * @since 2024-11-13 09:00
 */
public interface MenuRepository {

    /**
     * 查询菜单
     *
     * @param menuId 菜单 ID
     * @return 菜单
     */
    Optional<Menu> find(MenuId menuId);

    /**
     * 查询菜单列表
     *
     * @param parentMenuId 父菜单 ID
     * @return 菜单列表
     */
    List<Menu> findByParent(MenuId parentMenuId);

    /**
     * 保存菜单
     *
     * @param menu 菜单
     * @return 菜单
     */
    Menu save(Menu menu);

    /**
     * 移除菜单
     *
     * @param menuId 菜单 ID
     * @return 是否操作成功
     */
    boolean remove(MenuId menuId);

    /**
     * 查询全部菜单
     *
     * @return 全部菜单
     */
    List<Menu> findAll();

    /**
     * 分页查询菜单列表
     *
     * @param keyword  关键词
     * @param pageNum  当前页
     * @param pageSize 当前页条数
     * @return 菜单列表
     */
    PageWrapper<Menu> findByPage(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 查询目录和菜单
     *
     * @return 菜单列表
     */
    List<Menu> findCatalogAndMenu();

}
