package com.ning.controller;

import com.ning.api.user.RemoteUserService;
import com.ning.model.Result;
import com.ning.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ExamController {

    @Resource
    RemoteUserService remoteUserService;

    @GetMapping(value = {"/", "/index"})
    public Result<String> index() {
        System.out.println(" ========== exam index ========== ");
        return Result.ok("exam index");
    }

    @GetMapping(value = "/users")
    public Result<List<User>> users(HttpServletRequest request) {
        String accessToken = request.getParameter("access_token");
        return remoteUserService.selectUsers(accessToken);
    }

}
