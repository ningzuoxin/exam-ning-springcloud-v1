package com.ning.dao;

import com.ning.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author ningning
 * @since 2021-06-16
 */
public interface MenuDao extends BaseMapper<Menu> {

    List<Menu> selectMenusByUserId(@Param("userId") Long userId);

}
