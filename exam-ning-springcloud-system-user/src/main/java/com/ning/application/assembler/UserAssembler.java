package com.ning.application.assembler;

import com.ning.application.dto.UserDTO;
import com.ning.domain.entity.User;
import com.ning.infrastructure.common.model.PageWrapper;
import com.ning.interfaces.request.AddUserRequest;
import com.ning.interfaces.request.UpdateUserRequest;
import org.mapstruct.*;
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
    UserDTO toDTO(User entity);

    List<UserDTO> toDTOList(List<User> entityList);

    PageWrapper<UserDTO> toDTOPageList(PageWrapper<User> pageEntityList);

    UserDTO toDTO(AddUserRequest request);

    UserDTO toDTO(UpdateUserRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "salt", ignore = true)
    @Mapping(target = "roleId", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget User entity, UserDTO dto);

}
