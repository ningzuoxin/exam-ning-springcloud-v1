package com.ning.domain.repository;

import com.ning.domain.entity.Role;
import com.ning.domain.types.RoleId;
import com.ning.infrastructure.common.model.PageWrapper;

import java.util.List;
import java.util.Optional;

/**
 * 角色仓储
 *
 * @author zuoxin.ning
 * @since 2024-10-30 21:30
 */
public interface RoleRepository {

    /**
     * 查询角色
     *
     * @param roleId 角色 ID
     * @return 角色
     */
    Optional<Role> find(RoleId roleId);

    /**
     * 保存角色
     *
     * @param role 角色
     * @return 角色
     */
    Role save(Role role);

    /**
     * 删除角色
     *
     * @param roleId 角色 ID
     * @return 是否操作成功
     */
    boolean remove(RoleId roleId);

    /**
     * 查询全部角色
     *
     * @return 全部角色
     */
    List<Role> findAll();

    /**
     * 分页查询角色列表
     *
     * @param keyword  关键词
     * @param pageNum  当前页
     * @param pageSize 当前页条数
     * @return 角色列表
     */
    PageWrapper<Role> findByPage(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 统计角色数
     *
     * @param roleKey 角色代码
     * @return 角色数
     */
    long countByRoleKey(String roleKey);

}
