package com.ning.service;

import com.ning.manager.RoleManager;
import com.ning.model.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleService {

    @Resource
    RoleManager roleManager;

    public Result selectPage(String keyword, Integer pNum, Integer pSize) {
        return Result.ok(roleManager.selectPage(keyword, pNum, pSize));
    }

}
