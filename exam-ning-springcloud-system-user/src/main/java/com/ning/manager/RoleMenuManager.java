package com.ning.manager;

import com.ning.dao.RoleMenuDao;
import com.ning.entity.RoleMenu;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RoleMenuManager {

    @Resource
    RoleMenuDao roleMenuDao;

    public Integer insert(RoleMenu roleMenu) {
        return roleMenuDao.insert(roleMenu);
    }

}
