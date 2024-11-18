package com.ning.infrastructure.persistence.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ning.domain.entity.Role;
import com.ning.domain.repository.RoleRepository;
import com.ning.domain.types.RoleId;
import com.ning.infrastructure.common.model.PageWrapper;
import com.ning.infrastructure.persistence.converter.RoleConverter;
import com.ning.infrastructure.persistence.dao.RoleDao;
import com.ning.infrastructure.persistence.model.RoleDO;
import com.ning.infrastructure.utils.SnowFlake;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 角色仓储实现类
 *
 * @author zuoxin.ning
 * @since 2024-10-30 21:30
 */
@Repository
@RequiredArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {

    private final RoleDao roleDao;
    private final RoleConverter roleConverter = RoleConverter.INSTANCE;

    /**
     * 查询角色
     *
     * @param roleId 角色 ID
     * @return 角色
     */
    @Override
    public Optional<Role> find(RoleId roleId) {
        return this.findByUid(roleId.getValue()).map(roleConverter::toEntity);
    }

    /**
     * 保存角色
     *
     * @param role 角色
     * @return 角色
     */
    @Override
    public Role save(Role role) {
        RoleDO roleDO = roleConverter.toDo(role);
        if (Objects.isNull(roleDO.getUid())) {
            roleDO.setUid(SnowFlake.ID.nextId());
            roleDao.insert(roleDO);
            return roleConverter.toEntity(roleDO);
        } else {
            Optional<RoleDO> roleDOOpt = this.findByUid(role.getId().getValue());
            if (!roleDOOpt.isPresent()) {
                throw new IllegalArgumentException("Role not exits, id: " + role.getId().getValue());
            }

            RoleDO dbRoleDo = roleDOOpt.get();
            roleConverter.updateDO(dbRoleDo, roleDO);
            roleDao.updateById(dbRoleDo);
            return roleConverter.toEntity(dbRoleDo);
        }
    }

    /**
     * 删除角色
     *
     * @param roleId 角色 ID
     * @return 是否操作成功
     */
    @Override
    public boolean remove(RoleId roleId) {
        Optional<RoleDO> roleDOOpt = this.findByUid(roleId.getValue());
        if (!roleDOOpt.isPresent()) {
            return true;
        }

        RoleDO roleDO = roleDOOpt.get();
        roleDO.setIsDeleted(1);
        roleDao.updateById(roleDO);
        return true;
    }

    /**
     * 查询全部角色
     *
     * @return 全部角色
     */
    @Override
    public List<Role> findAll() {
        QueryWrapper<RoleDO> wrapper = new QueryWrapper<>();
        wrapper.eq("is_deleted", 0);
        List<RoleDO> roleDOList = roleDao.selectList(wrapper);
        return roleConverter.toEntityList(roleDOList);
    }

    /**
     * 分页查询角色列表
     *
     * @param keyword  关键词
     * @param pageNum  当前页
     * @param pageSize 当前页条数
     * @return 角色列表
     */
    @Override
    public PageWrapper<Role> findByPage(String keyword, Integer pageNum, Integer pageSize) {
        // 分页对象
        IPage<RoleDO> iPage = new Page<>(pageNum, pageSize);

        // 查询对象
        LambdaQueryWrapper<RoleDO> wrapper = new QueryWrapper<RoleDO>().lambda();
        wrapper.eq(RoleDO::getIsDeleted, 0);
        if (StrUtil.isNotEmpty(keyword)) {
            wrapper.like(RoleDO::getRoleName, keyword);
        }
        wrapper.orderByDesc(RoleDO::getUpdateTime);
        wrapper.orderByAsc(RoleDO::getSortNum);

        IPage<RoleDO> roleDOIPage = roleDao.selectPage(iPage, wrapper);
        return PageWrapper.<Role>builder()
                .total(roleDOIPage.getTotal())
                .pageNum(pageNum)
                .pageSize(pageSize)
                .data(roleConverter.toEntityList(roleDOIPage.getRecords()))
                .build();
    }

    /**
     * 统计角色数
     *
     * @param roleKey 角色代码
     * @return 角色数
     */
    @Override
    public long countByRoleKey(String roleKey) {
        QueryWrapper<RoleDO> wrapper = new QueryWrapper<>();
        wrapper.eq("role_key", roleKey);
        wrapper.eq("is_deleted", 0);
        return roleDao.selectCount(wrapper);
    }

    private Optional<RoleDO> findByUid(Long uid) {
        QueryWrapper<RoleDO> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid);
        wrapper.eq("is_deleted", 0);
        return Optional.ofNullable(roleDao.selectOne(wrapper));
    }

}