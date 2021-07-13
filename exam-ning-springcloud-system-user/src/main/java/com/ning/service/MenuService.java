package com.ning.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ning.common.model.MetaVo;
import com.ning.common.model.RouterVo;
import com.ning.common.model.TreeSelect;
import com.ning.entity.Menu;
import com.ning.manager.MenuManager;
import com.ning.model.Result;

import com.ning.utils.SecurityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuService {

    @Resource
    MenuManager menuManager;

    /**
     * 分页查询菜单列表
     *
     * @param keyword
     * @param pNum
     * @param pSize
     * @return
     */
    public Result selectPage(String keyword, Integer pNum, Integer pSize) {
        return Result.ok(menuManager.selectPage(keyword, pNum, pSize));
    }

    /**
     * 添加菜单
     *
     * @param menu
     * @return
     */
    public Result add(Menu menu) {
        Integer result = menuManager.insert(menu);
        if (result == 1) {
            return Result.ok(menu);
        } else {
            return Result.fail("添加失败");
        }
    }

    /**
     * 查询目录和菜单
     *
     * @return
     */
    public Result queryMC() {
        return Result.ok(menuManager.queryMC());
    }

    /**
     * 查询菜单树形列表
     *
     * @return
     */
    public Result tree() {
        List<Menu> menus = buildMenuTree(menuManager.selectAll());
        return Result.ok(menus.stream().map(TreeSelect::new).collect(Collectors.toList()));
    }

    /**
     * 构建前端所需要树结构
     *
     * @param menus 菜单列表
     * @return 树结构列表
     */
    public List<Menu> buildMenuTree(List<Menu> menus) {
        List<Menu> returnList = new ArrayList();
        for (Iterator<Menu> iterator = menus.iterator(); iterator.hasNext(); ) {
            Menu t = iterator.next();
            // 根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == 0) {
                recursionFn(menus, t);
                returnList.add(t);
            }
        }
        if (returnList.isEmpty()) {
            returnList = menus;
        }
        return returnList;
    }

    /**
     * 根据id查询菜单
     *
     * @param id
     * @return
     */
    public Result get(Long id) {
        Menu menu = menuManager.selectById(id);
        if (ObjectUtil.isEmpty(menu)) {
            return Result.fail("不存在的菜单");
        }
        if ("1".equals(menu.getStatus())) {
            return Result.fail("菜单已停用");
        }
        return Result.ok(menu);
    }

    /**
     * 修改菜单
     *
     * @param menu
     * @return
     */
    public Result update(Menu menu) {
        Menu recMenu = menuManager.selectById(menu.getMenuId());
        if (ObjectUtil.isEmpty(recMenu)) {
            return Result.fail("不存在的菜单");
        }
        if ("1".equals(recMenu.getStatus())) {
            return Result.fail("菜单已停用");
        }

        BeanUtil.copyProperties(menu, recMenu, "menuId", "createBy", "createTime", "status");
        Integer result = menuManager.updateById(recMenu);
        if (result == 1) {
            return Result.ok(recMenu);
        } else {
            return Result.fail("修改失败");
        }
    }

    /**
     * 删除菜单
     *
     * @param id
     * @return
     */
    public Result delete(Long id) {
        Menu menu = menuManager.selectById(id);
        if (ObjectUtil.isEmpty(menu)) {
            return Result.fail("不存在的菜单");
        }
        if ("1".equals(menu.getStatus())) {
            return Result.fail("菜单已停用");
        }

        menu.setStatus("1");
        menu.setUpdateTime(LocalDateTime.now());
        menu.setUpdateBy(SecurityUtils.getLoginUser().getUserId() + "");
        Integer result = menuManager.updateById(menu);
        if (result == 1) {
            return Result.ok(menu);
        } else {
            return Result.fail("删除失败");
        }
    }

    /**
     * 查询路由
     *
     * @param userId
     * @return
     */
    public Result getRouters(Long userId) {
        List<Menu> menus = buildMenuTree(menuManager.selectMenusByUserId(userId));
        return Result.ok(buildMenus(menus));
    }

    public List<RouterVo> buildMenus(List<Menu> menus) {
        List<RouterVo> routers = new LinkedList();
        for (Menu menu : menus) {
            RouterVo router = new RouterVo();
            router.setHidden("1".equals(menu.getVisible()));
            router.setName(getRouteName(menu));
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));
            router.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon()));
            List<Menu> cMenus = menu.getChildren();
            if (cMenus != null && !cMenus.isEmpty() && cMenus.size() > 0 && "M".equals(menu.getMenuType())) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            } else if (isMeunFrame(menu)) {
                List<RouterVo> childrenList = new ArrayList<RouterVo>();
                RouterVo children = new RouterVo();
                children.setPath(menu.getPath());
                children.setComponent(menu.getComponent());
                children.setName(StringUtils.capitalize(menu.getPath()));
                children.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon()));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 获取路由名称
     *
     * @param menu 菜单信息
     * @return 路由名称
     */
    public String getRouteName(Menu menu) {
        String routerName = StringUtils.capitalize(menu.getPath());
        // 非外链并且是一级目录（类型为目录）
        if (isMeunFrame(menu)) {
            routerName = StringUtils.EMPTY;
        }
        return routerName;
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(Menu menu) {
        String routerPath = menu.getPath();
        // 非外链并且是一级目录（类型为目录）
        if (0 == menu.getParentId().intValue() && "M".equals(menu.getMenuType()) && "1".equals(menu.getIsFrame())) {
            routerPath = "/" + menu.getPath();
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMeunFrame(menu)) {
            routerPath = "/";
        }
        return routerPath;
    }

    /**
     * 获取组件信息
     *
     * @param menu 菜单信息
     * @return 组件信息
     */
    public String getComponent(Menu menu) {
        String component = "Layout";
        if (StringUtils.isNotEmpty(menu.getComponent()) && !isMeunFrame(menu)) {
            component = menu.getComponent();
        }
        return component;
    }

    /**
     * 是否为菜单内部跳转
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isMeunFrame(Menu menu) {
        return menu.getParentId().intValue() == 0 && "C".equals(menu.getMenuType()) && menu.getIsFrame().equals("1");
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list     分类表
     * @param parentId 传入的父节点ID
     * @return
     */
    public List<Menu> getChildPerms(List<Menu> list, int parentId) {
        List<Menu> returnList = new ArrayList();
        for (Iterator<Menu> iterator = list.iterator(); iterator.hasNext(); ) {
            Menu t = iterator.next();
            // 根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == parentId) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFn(List<Menu> list, Menu t) {
        // 得到子节点列表
        List<Menu> childList = getChildList(list, t);
        t.setChildren(childList);
        for (Menu tChild : childList) {
            if (hasChild(list, tChild)) {
                // 判断是否有子节点
                Iterator<Menu> it = childList.iterator();
                while (it.hasNext()) {
                    Menu n = it.next();
                    recursionFn(list, n);
                }
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<Menu> getChildList(List<Menu> list, Menu t) {
        List<Menu> menuList = new ArrayList();
        Iterator<Menu> it = list.iterator();
        while (it.hasNext()) {
            Menu n = it.next();
            if (n.getParentId().longValue() == t.getMenuId().longValue()) {
                menuList.add(n);
            }
        }
        return menuList;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<Menu> list, Menu t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }

}
