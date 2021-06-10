package com.ning.controller;

import com.ning.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/oauth")
public class OauthController {

    @Autowired
    private TokenEndpoint tokenEndpoint;

    @RequestMapping("/user")
    public Principal user(Principal user) {
        log.info("OauthController # user Principal={}", user);
        return user;
    }

    /**
     * 重写认证端口(/oauth/token)，以自定义格式返回数据
     *
     * @param principal
     * @param parameters
     * @return
     * @throws HttpRequestMethodNotSupportedException
     */
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public Result postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", oAuth2AccessToken.getValue());
        map.put("expires_in", oAuth2AccessToken.getExpiresIn());
        return Result.ok(map);
    }

}
