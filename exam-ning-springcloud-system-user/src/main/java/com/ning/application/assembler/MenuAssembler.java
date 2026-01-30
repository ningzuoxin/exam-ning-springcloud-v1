package com.ning.application.assembler;

import com.ning.application.dto.MenuDTO;
import com.ning.domain.entity.Menu;
import com.ning.infrastructure.common.model.PageWrapper;
import com.ning.interfaces.request.AddMenuRequest;
import com.ning.interfaces.request.UpdateMenuRequest;
import org.mapstruct.*;
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
    MenuDTO toDTO(Menu entity);

    List<MenuDTO> toDTOList(List<Menu> entityList);

    @Mapping(target = "data", expression = "java(toDTOList(pageEntityList.getData()))")
    @Mapping(target = "total", source = "total")
    @Mapping(target = "pageNum", source = "pageNum")
    @Mapping(target = "pageSize", source = "pageSize")
    PageWrapper<MenuDTO> toDTOPageList(PageWrapper<Menu> pageEntityList);

    MenuDTO toDTO(AddMenuRequest request);

    MenuDTO toDTO(UpdateMenuRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "parentId", expression = "java(new MenuId(dto.getParentId()))")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget Menu entity, MenuDTO dto);

}
