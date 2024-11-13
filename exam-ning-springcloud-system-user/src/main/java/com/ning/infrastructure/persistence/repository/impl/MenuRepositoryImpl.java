package com.ning.infrastructure.persistence.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ning.domain.entity.Menu;
import com.ning.domain.repository.MenuRepository;
import com.ning.domain.types.MenuId;
import com.ning.infrastructure.common.model.PageWrapper;
import com.ning.infrastructure.persistence.converter.MenuConverter;
import com.ning.infrastructure.persistence.dao.MenuDao;
import com.ning.infrastructure.persistence.model.MenuDO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 菜单仓储实现类
 *
 * @author zuoxin.ning
 * @since 2024-11-13 09:00
 */
@Repository
@RequiredArgsConstructor
public class MenuRepositoryImpl implements MenuRepository {

    private final MenuDao menuDao;
    private final MenuConverter menuConverter = MenuConverter.INSTANCE;

    /**
     * 查询菜单
     *
     * @param menuId 菜单 ID
     * @return 菜单
     */
    @Override
    public Optional<Menu> find(MenuId menuId) {
        return this.findByUid(menuId.getValue()).map(menuConverter::toEntity);
    }

    /**
     * 查询菜单列表
     *
     * @param parentMenuId 父菜单 ID
     * @return 菜单列表
     */
    @Override
    public List<Menu> findByParent(MenuId parentMenuId) {
        List<MenuDO> menuDOList = this.findByParentUid(parentMenuId.getValue());
        return menuConverter.toEntityList(menuDOList);
    }

    /**
     * 保存菜单
     *
     * @param menu 菜单
     * @return 菜单
     */
    @Override
    public Menu save(Menu menu) {
        return null;
    }

    @Override
    public boolean remove(MenuId menuId) {
        return false;
    }

    @Override
    public List<Menu> findAll() {
        return null;
    }

    @Override
    public PageWrapper<Menu> findByPage(String keyword, Integer pageNum, Integer pageSize) {
        return null;
    }

    private Optional<MenuDO> findByUid(Long uid) {
        QueryWrapper<MenuDO> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid);
        wrapper.eq("is_deleted", 0);
        return Optional.ofNullable(menuDao.selectOne(wrapper));
    }

    private List<MenuDO> findByParentUid(Long parentUid) {
        QueryWrapper<MenuDO> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_uid", parentUid);
        wrapper.eq("is_deleted", 0);
        return menuDao.selectList(wrapper);
    }

}
