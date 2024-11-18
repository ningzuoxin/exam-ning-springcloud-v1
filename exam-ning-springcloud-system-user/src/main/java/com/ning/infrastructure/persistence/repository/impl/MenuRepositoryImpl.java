package com.ning.infrastructure.persistence.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ning.domain.entity.Menu;
import com.ning.domain.enums.MenuStatusEnum;
import com.ning.domain.enums.MenuTypeEnum;
import com.ning.domain.repository.MenuRepository;
import com.ning.domain.types.MenuId;
import com.ning.domain.types.UserId;
import com.ning.infrastructure.common.model.PageWrapper;
import com.ning.infrastructure.persistence.converter.MenuConverter;
import com.ning.infrastructure.persistence.dao.MenuDao;
import com.ning.infrastructure.persistence.model.MenuDO;
import com.ning.infrastructure.utils.SnowFlake;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
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
        MenuDO menuDO = menuConverter.toDO(menu);
        if (Objects.isNull(menuDO.getUid())) {
            menuDO.setUid(SnowFlake.ID.nextId());
            menuDao.insert(menuDO);
            return menuConverter.toEntity(menuDO);
        } else {
            Optional<MenuDO> menuDOOpt = this.findByUid(menuDO.getUid());
            if (!menuDOOpt.isPresent()) {
                throw new IllegalArgumentException("Menu not exits, id: " + menu.getId().getValue());
            }

            MenuDO dbMenuDO = menuDOOpt.get();
            menuConverter.updateDO(dbMenuDO, menuDO);
            menuDao.updateById(dbMenuDO);
            return menuConverter.toEntity(dbMenuDO);
        }
    }

    /**
     * 移除菜单
     *
     * @param menuId 菜单 ID
     * @return 是否操作成功
     */
    @Override
    public boolean remove(MenuId menuId) {
        Optional<MenuDO> menuDOOpt = this.findByUid(menuId.getValue());
        if (!menuDOOpt.isPresent()) {
            return true;
        }

        MenuDO menuDO = menuDOOpt.get();
        menuDO.setIsDeleted(1);
        menuDao.updateById(menuDO);
        return false;
    }

    /**
     * 查询全部菜单
     *
     * @return 全部菜单
     */
    @Override
    public List<Menu> findAll() {
        QueryWrapper<MenuDO> wrapper = new QueryWrapper<>();
        wrapper.eq("is_deleted", 0);
        List<MenuDO> menuDOList = menuDao.selectList(wrapper);
        return menuConverter.toEntityList(menuDOList);
    }

    /**
     * 分页查询菜单列表
     *
     * @param keyword  关键词
     * @param pageNum  当前页
     * @param pageSize 当前页条数
     * @return 菜单列表
     */
    @Override
    public PageWrapper<Menu> findByPage(String keyword, Integer pageNum, Integer pageSize) {
        // 分页对象
        IPage<MenuDO> iPage = new Page<>(pageNum, pageSize);

        // 查询对象
        LambdaQueryWrapper<MenuDO> wrapper = new QueryWrapper<MenuDO>().lambda();
        wrapper.eq(MenuDO::getIsDeleted, 0);
        if (StrUtil.isNotEmpty(keyword)) {
            wrapper.like(MenuDO::getMenuName, keyword);
        }
        wrapper.orderByAsc(MenuDO::getParentUid, MenuDO::getSortNum, MenuDO::getUpdateTime);

        IPage<MenuDO> menuDOIPage = menuDao.selectPage(iPage, wrapper);
        return PageWrapper.<Menu>builder()
                .total(menuDOIPage.getTotal())
                .pageNum(pageNum)
                .pageSize(pageSize)
                .data(menuConverter.toEntityList(menuDOIPage.getRecords()))
                .build();
    }

    /**
     * 查询目录和菜单
     *
     * @return 菜单列表
     */
    @Override
    public List<Menu> findCatalogAndMenu() {
        // 查询对象
        LambdaQueryWrapper<MenuDO> wrapper = new QueryWrapper<MenuDO>().lambda();
        wrapper.eq(MenuDO::getIsDeleted, 0);
        wrapper.eq(MenuDO::getStatus, MenuStatusEnum.NORMAL.getValue());
        wrapper.eq(MenuDO::getMenuType, MenuTypeEnum.Catalog.getValue()).or().eq(MenuDO::getMenuType, MenuTypeEnum.Menu.getValue());
        wrapper.orderByAsc(MenuDO::getParentUid, MenuDO::getSortNum, MenuDO::getUpdateTime);
        List<MenuDO> menuDOList = menuDao.selectList(wrapper);
        return menuConverter.toEntityList(menuDOList);
    }

    /**
     * 查询用户的菜单列表
     *
     * @param userId 用户 ID
     * @return 菜单列表
     */
    @Override
    public List<Menu> findByUserId(UserId userId) {
        List<MenuDO> menuDOList = menuDao.selectMenusByUserId(userId.getValue());
        return menuConverter.toEntityList(menuDOList);
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
