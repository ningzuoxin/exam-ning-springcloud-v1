package com.ning.infrastructure.persistence.converter;

import com.ning.domain.entity.User;
import com.ning.infrastructure.persistence.model.UserDO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户实体、DO 转换器
 *
 * @author zuoxin.ning
 * @since 2024-03-24 08:00
 */
@Mapper
public interface UserConverter {

    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uid", source = "id.value")
    @Mapping(target = "username", source = "username.value")
    @Mapping(target = "roleId", source = "roleId.value")
    UserDO toDO(User user);

    @Mapping(target = "id", expression = "java(new UserId(userDO.getUid()))")
    @Mapping(target = "username", expression = "java(new Username(userDO.getUsername()))")
    @Mapping(target = "roleId", expression = "java(new RoleId(userDO.getRoleId()))")
    User toEntity(UserDO userDO);

    List<User> toEntityList(List<UserDO> userDOList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDO(@MappingTarget UserDO dbUserDO, UserDO modifiedUserDO);

}
