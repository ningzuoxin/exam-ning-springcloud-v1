package com.ning.infrastructure.persistence.dao;

import com.ning.infrastructure.persistence.model.MenuDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统菜单表 Mapper 接口
 * </p>
 *
 * @author zuoxin.ning
 * @since 2025-01-01 00:00:01
 */
public interface MenuDao extends BaseMapper<MenuDO> {

    List<MenuDO> selectMenusByUserId(@Param("userId") Long userId);

}
