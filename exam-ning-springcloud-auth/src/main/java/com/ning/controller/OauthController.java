package com.ning.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.ning.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/oauth")
public class OauthController {

    @Resource
    private TokenStore tokenStore;

    @Resource
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
        Map<String, Object> additionalInformation = oAuth2AccessToken.getAdditionalInformation();

        Map<String, Object> map = new HashMap<>();
        map.put("access_token", oAuth2AccessToken.getValue());
        map.put("expires_in", oAuth2AccessToken.getExpiresIn());
        map.put("user_id", additionalInformation.get("user_id"));
        map.put("user_name", additionalInformation.get("user_name"));
        return Result.ok(map);
    }

    @GetMapping("/logout")
    public Result<?> logout(@RequestHeader(value = "Authorization", required = false) String token) {
        if (StrUtil.isEmpty(token)) {
            return Result.ok("");
        }

        String tokenValue = token.replace(OAuth2AccessToken.BEARER_TYPE, StrUtil.EMPTY).trim();
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
        if (accessToken == null || StrUtil.isEmpty(accessToken.getValue())) {
            return Result.ok("");
        }

        // 清空 access token
        tokenStore.removeAccessToken(accessToken);

        // 清空 refresh token
        OAuth2RefreshToken refreshToken = accessToken.getRefreshToken();
        if (ObjectUtil.isNotEmpty(refreshToken)) {
            tokenStore.removeRefreshToken(refreshToken);
        }

        return Result.ok("");
    }

}
