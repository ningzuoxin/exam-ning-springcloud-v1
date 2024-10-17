package com.ning.application.assembler;

import com.ning.application.dto.UserDTO;
import com.ning.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 用户实体、DTO 转换器
 *
 * @author zuoxin.ning
 * @since 2024-10-17 09:00
 */
@Mapper
public interface UserAssembler {

    UserAssembler INSTANCE = Mappers.getMapper(UserAssembler.class);

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "username", source = "username.value")
    @Mapping(target = "roleId", source = "roleId.value")
    UserDTO toDTO(User user);

    List<UserDTO> toDTOList(List<User> userList);

}
