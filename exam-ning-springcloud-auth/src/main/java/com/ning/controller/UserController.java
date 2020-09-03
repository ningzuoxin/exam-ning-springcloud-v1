package com.ning.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 身份信息获取
 *
 * @author ruoyi
 */
@Slf4j
@RestController
@RequestMapping("/oauth")
public class UserController {

    @RequestMapping("/user")
    public Principal user(Principal user) {
        log.info("UserController user => " + user);
        return user;
    }

}
