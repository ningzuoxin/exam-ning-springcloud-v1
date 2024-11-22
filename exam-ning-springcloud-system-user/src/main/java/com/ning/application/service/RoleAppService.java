package com.ning.application.service;

import com.ning.application.assembler.RoleAssembler;
import com.ning.application.dto.RoleDTO;
import com.ning.constant.BusinessCodeEnum;
import com.ning.domain.entity.Role;
import com.ning.domain.repository.RoleRepository;
import com.ning.domain.types.RoleId;
import com.ning.exception.BusinessException;
import com.ning.infrastructure.common.model.PageWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 角色应用服务
 *
 * @author zuoxin.ning
 * @since 2024-10-31 08:30
 */
@Service
@RequiredArgsConstructor
public class RoleAppService {

    private final RoleRepository roleRepository;
    private final RoleAssembler roleAssembler = RoleAssembler.INSTANCE;

    /**
     * 分页查询角色列表
     *
     * @param keyword  关键词
     * @param pageNum  当前页
     * @param pageSize 当前页条数
     * @return 角色列表
     */
    public PageWrapper<RoleDTO> page(String keyword, Integer pageNum, Integer pageSize) {
        PageWrapper<Role> pageRoleList = roleRepository.findByPage(keyword, pageNum, pageSize);
        return roleAssembler.toDTOPageList(pageRoleList);
    }

    /**
     * 添加角色
     *
     * @param roleDTO 角色
     * @return 角色
     */
    public RoleDTO add(RoleDTO roleDTO) {
        long count = roleRepository.countByRoleKey(roleDTO.getRoleKey());
        if (count > 0) {
            throw new BusinessException(BusinessCodeEnum.ROLE_KEY_EXISTS);
        }

        Role role = new Role(new RoleId(null),
                roleDTO.getRoleName(),
                roleDTO.getRoleKey(),
                roleDTO.getSortNum());
        // todo 角色相关的菜单处理

        role = roleRepository.save(role);
        return roleAssembler.toDTO(role);
    }

    /**
     * 查询角色
     *
     * @param id 角色 ID
     * @return 角色
     */
    public RoleDTO get(Long id) {
        RoleId roleId = new RoleId(id);
        Optional<Role> roleOpt = roleRepository.find(roleId);
        return roleOpt.map(roleAssembler::toDTO).orElse(null);
    }

    /**
     * 删除角色
     *
     * @param id 角色 ID
     * @return 是否操作成功
     */
    public boolean delete(Long id) {
        RoleId roleId = new RoleId(id);
        return roleRepository.remove(roleId);
    }

    /**
     * 修改角色
     *
     * @param roleDTO 角色
     * @return 角色
     */
    public RoleDTO update(RoleDTO roleDTO) {
        RoleId roleId = new RoleId(roleDTO.getId());
        Optional<Role> roleOpt = roleRepository.find(roleId);
        if (!roleOpt.isPresent()) {
            throw new BusinessException(BusinessCodeEnum.ROLE_NOT_EXISTS);
        }

        Role role = roleOpt.get();
        roleAssembler.updateEntity(role, roleDTO);

        role = roleRepository.save(role);
        return roleAssembler.toDTO(role);
    }

    /**
     * 查询全部角色
     *
     * @return 全部角色
     */
    public List<RoleDTO> all() {
        List<Role> roleList = roleRepository.findAll();
        return roleAssembler.toDTOList(roleList);
    }

}
