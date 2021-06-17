package com.ning.service;

import com.ning.entity.Menu;
import com.ning.manager.MenuManager;
import com.ning.model.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}
