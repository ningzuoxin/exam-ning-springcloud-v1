package com.ning.application.service;

import com.ning.application.assembler.MenuAssembler;
import com.ning.application.dto.MenuDTO;
import com.ning.domain.entity.Menu;
import com.ning.domain.repository.MenuRepository;
import com.ning.domain.types.MenuId;
import com.ning.infrastructure.common.model.PageWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
