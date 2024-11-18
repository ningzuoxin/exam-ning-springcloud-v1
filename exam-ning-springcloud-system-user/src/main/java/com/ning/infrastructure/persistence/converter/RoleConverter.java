package com.ning.infrastructure.persistence.converter;

import com.ning.domain.entity.Role;
import com.ning.infrastructure.persistence.model.RoleDO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 角色实体、DO 转换器
 *
 * @author zuoxin.ning
 * @since 2024-10-30 21:30
 */
@Mapper
public interface RoleConverter {

    RoleConverter INSTANCE = Mappers.getMapper(RoleConverter.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uid", source = "id.value")
    RoleDO toDo(Role entity);

    @Mapping(target = "id", expression = "java(new RoleId(dataObject.getUid()))")
    Role toEntity(RoleDO dataObject);

    List<Role> toEntityList(List<RoleDO> dataObjectList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDO(@MappingTarget RoleDO dbDataObject, RoleDO modifiedDataObject);

}