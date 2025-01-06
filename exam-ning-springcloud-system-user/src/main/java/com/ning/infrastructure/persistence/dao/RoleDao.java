package com.ning.infrastructure.persistence.dao;

import com.ning.infrastructure.persistence.model.RoleDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 系统角色表 Mapper 接口
 * </p>
 *
 * @author zuoxin.ning
 * @since 2025-01-01 00:00:01
 */
public interface RoleDao extends BaseMapper<RoleDO> {

    /**
     * 根据用户 id 查询角色代码
     *
     * @param userId 用户 ID
     * @return 角色代码
     */
    String getRoleKeyByUserId(@Param("userId") Long userId);

}
