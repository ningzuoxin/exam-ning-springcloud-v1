package com.ning.infrastructure.persistence.converter;

import com.ning.domain.entity.User;
import com.ning.infrastructure.persistence.model.UserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 用户实体、DO 转换器
 *
 * @author zuoxin.ning
 * @since 2024-03-24 08:00
 */
@Mapper
public interface UserConverter {

    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    UserDO toDO(User user);

    User toEntity(UserDO userDO);

}
