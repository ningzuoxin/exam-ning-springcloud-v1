package com.ning.application.service;

import cn.hutool.core.collection.CollectionUtil;
import com.ning.application.assembler.MenuAssembler;
import com.ning.application.dto.MenuDTO;
import com.ning.application.dto.MetaDTO;
import com.ning.application.dto.RouterDTO;
import com.ning.application.dto.TreeSelectDTO;
import com.ning.constant.BusinessCodeEnum;
import com.ning.domain.entity.Menu;
import com.ning.domain.enums.MenuStatusEnum;
import com.ning.domain.enums.MenuTypeEnum;
import com.ning.domain.repository.MenuRepository;
import com.ning.domain.types.MenuId;
import com.ning.domain.types.UserId;
import com.ning.exception.BusinessException;
import com.ning.infrastructure.common.model.PageWrapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 菜单应用服务
 *
 * @author zuoxin.ning
 * @since 2024-11-13 20:30
 */
@Service
@RequiredArgsConstructor
public class MenuAppService {

    private final MenuRepository menuRepository;
    private final MenuAssembler menuAssembler = MenuAssembler.INSTANCE;

    /**
     * 分页查询菜单列表
     *
     * @param keyword  关键词
     * @param pageNum  当前页
     * @param pageSize 当前页条数
     * @return 菜单列表
     */
    public PageWrapper<MenuDTO> findByPage(String keyword, Integer pageNum, Integer pageSize) {
        PageWrapper<Menu> pageMenuList = menuRepository.findByPage(keyword, pageNum, pageSize);
        return menuAssembler.toDTOPageList(pageMenuList);
    }

    /**
     * 添加菜单
     *
     * @param menuDTO 菜单
     * @return 菜单
     */
    public MenuDTO add(MenuDTO menuDTO) {
        Menu menu = new Menu(new MenuId(null),
                menuDTO.getMenuName(),
                new MenuId(menuDTO.getParentId()),
                menuDTO.getSortNum(),
                menuDTO.getPath(),
                menuDTO.getComponent(),
                menuDTO.getIsFrame(),
                menuDTO.getMenuType(),
                menuDTO.getPerms(),
                menuDTO.getIcon());
        menu = menuRepository.save(menu);
        return menuAssembler.toDTO(menu);
    }

    /**
     * 查询目录和菜单
     *
     * @return 菜单列表
     */
    public List<MenuDTO> findCatalogAndMenu() {
        List<Menu> menuList = menuRepository.findCatalogAndMenu();
        return menuAssembler.toDTOList(menuList);
    }

    /**
     * 查询树形菜单列表
     *
     * @return 菜单列表
     */
    public List<TreeSelectDTO> tree() {
        List<Menu> menuList = menuRepository.findAll();
        List<Menu> treeMenuList = Menu.tree(menuList);
        return treeMenuList.stream().map(TreeSelectDTO::new).collect(Collectors.toList());
    }

    /**
     * 查询用户的树形路由列表
     *
     * @param userId 用户 ID
     * @return 树形路由列表
     */
    public List<RouterDTO> getRouters(Long userId) {
        List<Menu> menuList = menuRepository.findByUserId(new UserId(userId));
        List<Menu> treeMenuList = Menu.tree(menuList);
        return this.buildRouterList(treeMenuList);
    }

    /**
     * 查询菜单
     *
     * @param menuId 菜单 ID
     * @return 菜单
     */
    public MenuDTO findById(Long menuId) {
        Optional<Menu> menuOpt = menuRepository.find(new MenuId(menuId));
        return menuOpt.map(menuAssembler::toDTO).orElse(null);
    }

    /**
     * 修改菜单
     *
     * @param menuDTO 菜单
     * @return 菜单
     */
    public MenuDTO update(MenuDTO menuDTO) {
        MenuId menuId = new MenuId(menuDTO.getId());
        Optional<Menu> menuOpt = menuRepository.find(menuId);
        if (!menuOpt.isPresent()) {
            throw new BusinessException(BusinessCodeEnum.ROLE_NOT_EXISTS);
        }

        Menu menu = menuOpt.get();
        menuAssembler.updateEntity(menu, menuDTO);
        menu = menuRepository.save(menu);
        return menuAssembler.toDTO(menu);
    }

    /**
     * 删除菜单
     *
     * @param id 菜单 ID
     * @return 是否操作成功
     */
    public boolean delete(Long id) {
        MenuId menuId = new MenuId(id);
        return menuRepository.remove(menuId);
    }

    /**
     * 构建树形路由列表
     *
     * @param menuList 树形菜单列表
     * @return 树形路由列表
     */
    private List<RouterDTO> buildRouterList(List<Menu> menuList) {
        LinkedList<RouterDTO> routerDTOList = new LinkedList<>();
        for (Menu menu : menuList) {
            RouterDTO router = new RouterDTO();
            router.setHidden(menu.getStatus() == MenuStatusEnum.NORMAL.getValue());
            router.setName(menu.getRouteName());
            router.setPath(menu.getRouterPath());
            router.setComponent(menu.getRouterComponent());
            router.setMeta(new MetaDTO(menu.getMenuName(), menu.getIcon()));
            List<Menu> childrenMenuList = menu.getChildren();
            if (CollectionUtil.isNotEmpty(childrenMenuList) && menu.getMenuType() == MenuTypeEnum.Catalog.getValue()) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(this.buildRouterList(childrenMenuList));
            } else if (menu.isMenuFrame()) {
                List<RouterDTO> childrenList = new ArrayList<>();
                RouterDTO children = new RouterDTO();
                children.setPath(menu.getPath());
                children.setComponent(menu.getRouterComponent());
                children.setName(StringUtils.capitalize(menu.getPath()));
                children.setMeta(new MetaDTO(menu.getMenuName(), menu.getIcon()));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routerDTOList.add(router);
        }
        return routerDTOList;
    }

}
