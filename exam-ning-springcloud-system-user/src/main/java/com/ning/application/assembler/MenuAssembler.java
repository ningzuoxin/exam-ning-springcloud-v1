package com.ning.application.assembler;

import com.ning.application.dto.MenuDTO;
import com.ning.domain.entity.Menu;
import com.ning.infrastructure.common.model.PageWrapper;
import com.ning.interfaces.request.AddMenuRequest;
import com.ning.interfaces.request.UpdateRoleRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 菜单实体、DTO 转换器
 *
 * @author zuoxin.ning
 * @since 2024-10-31 09:00
 */
@Mapper
public interface MenuAssembler {

    MenuAssembler INSTANCE = Mappers.getMapper(MenuAssembler.class);

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "parentId", source = "parentId.value")
    MenuDTO toDTO(Menu menu);

    List<MenuDTO> toDTOList(List<Menu> menuList);

    PageWrapper<MenuDTO> toDTOPageList(PageWrapper<Menu> pageMenuList);

    MenuDTO toDTO(AddMenuRequest request);

    MenuDTO toDTO(UpdateRoleRequest request);

}
