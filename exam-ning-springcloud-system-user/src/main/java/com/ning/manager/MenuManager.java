package com.ning.manager;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ning.dao.MenuDao;
import com.ning.entity.Menu;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MenuManager {

    @Resource
    MenuDao menuDao;

    /**
     * 分页查询菜单列表
     *
     * @param keyword
     * @param pNum
     * @param pSize
     * @return
     */
    public IPage<Menu> selectPage(String keyword, Integer pNum, Integer pSize) {
        // 分页对象
        IPage<Menu> iPage = new Page<>(pNum, pSize);

        // 查询对象
        LambdaQueryWrapper<Menu> wrapper = new QueryWrapper<Menu>().lambda();
        wrapper.eq(Menu::getStatus, "0");
        if (StrUtil.isNotEmpty(keyword)) {
            wrapper.like(Menu::getMenuName, keyword);
        }
        wrapper.orderByAsc(Menu::getParentId, Menu::getOrderNum, Menu::getUpdateTime);

        return menuDao.selectPage(iPage, wrapper);
    }

    /**
     * 添加菜单
     *
     * @param menu
     * @return
     */
    public Integer insert(Menu menu) {
        return menuDao.insert(menu);
    }

    /**
     * 查询目录和菜单
     *
     * @return
     */
    public List<Menu> queryMC() {
        // 查询对象
        LambdaQueryWrapper<Menu> wrapper = new QueryWrapper<Menu>().lambda();
        wrapper.eq(Menu::getStatus, "0");
        wrapper.like(Menu::getMenuType, "M").or().like(Menu::getMenuType, "C");
        wrapper.orderByAsc(Menu::getParentId, Menu::getOrderNum, Menu::getUpdateTime);
        return menuDao.selectList(wrapper);
    }

    /**
     * 查询所有菜单
     *
     * @return
     */
    public List<Menu> selectAll() {
        // 查询对象
        LambdaQueryWrapper<Menu> wrapper = new QueryWrapper<Menu>().lambda();
        wrapper.eq(Menu::getStatus, "0");
        return menuDao.selectList(wrapper);
    }

    /**
     * 根据用户id查询菜单列表
     *
     * @param userId
     * @return
     */
    public List<Menu> selectMenusByUserId(Long userId) {
        return menuDao.selectMenusByUserId(userId);
    }

    public Menu selectById(Long id) {
        return menuDao.selectById(id);
    }

    public Integer updateById(Menu menu) {
        return menuDao.updateById(menu);
    }

    /**
     * 根据用户id查询拥有的权限
     *
     * @param userId
     * @return
     */
    public List<String> listPermissionsByUserId(Long userId) {
        List<Menu> menus = this.selectMenusByUserId(userId);
        List<String> permissions = menus.stream().filter(m -> !"M".equals(m.getMenuType())).map(m -> m.getPerms()).collect(Collectors.toList());
        return permissions;
    }

}
