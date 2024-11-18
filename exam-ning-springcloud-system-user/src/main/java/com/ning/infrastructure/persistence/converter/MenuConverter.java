package com.ning.infrastructure.persistence.converter;

import com.ning.domain.entity.Menu;
import com.ning.infrastructure.persistence.model.MenuDO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 菜单实体、DO 转换器
 *
 * @author zuoxin.ning
 * @since 2024-11-13 09:00
 */
@Mapper
public interface MenuConverter {

    MenuConverter INSTANCE = Mappers.getMapper(MenuConverter.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uid", source = "id.value")
    @Mapping(target = "parentUid", source = "parentId.value")
    MenuDO toDO(Menu entity);

    @Mapping(target = "id", expression = "java(new MenuId(dataObject.getUid()))")
    @Mapping(target = "parentId", expression = "java(new MenuId(dataObject.getParentUid()))")
    Menu toEntity(MenuDO dataObject);

    List<Menu> toEntityList(List<MenuDO> dataObjectList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDO(@MappingTarget MenuDO dbDataObject, MenuDO modifiedDataObject);

}
