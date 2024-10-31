package com.ning.application.assembler;

import com.ning.application.dto.RoleDTO;
import com.ning.domain.entity.Role;
import com.ning.infrastructure.common.model.PageWrapper;
import com.ning.interfaces.request.AddRoleRequest;
import com.ning.interfaces.request.UpdateRoleRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 角色实体、DTO 转换器
 *
 * @author zuoxin.ning
 * @since 2024-10-31 09:00
 */
@Mapper
public interface RoleAssembler {

    RoleAssembler INSTANCE = Mappers.getMapper(RoleAssembler.class);

    @Mapping(target = "id", source = "id.value")
    RoleDTO toDTO(Role role);

    List<RoleDTO> toDTOList(List<Role> roleList);

    PageWrapper<RoleDTO> toDTOPageList(PageWrapper<Role> pageRoleList);

    RoleDTO toDTO(AddRoleRequest request);

    RoleDTO toDTO(UpdateRoleRequest request);

}
