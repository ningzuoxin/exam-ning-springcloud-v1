package com.ning.dao;

import com.ning.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author ningning
 * @since 2021-06-16
 */
public interface RoleDao extends BaseMapper<Role> {

    /**
     * 根据用户id查询角色代码
     *
     * @param userId
     * @return
     */
    String getRoleKeyByUserId(@Param("userId") Long userId);
}
