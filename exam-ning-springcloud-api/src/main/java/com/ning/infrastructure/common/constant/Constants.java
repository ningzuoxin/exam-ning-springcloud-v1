package com.ning.infrastructure.common.constant;

public class Constants {

    // client_id
    public static final String CLIENT_ID = "ning168168";

    // client_secret
    public static final String CLIENT_SECRET = "168668";

    // redirectUri
    public static final String REDIRECT_URI = "http://127.0.0.1:8080";

    // postLogoutRedirectUri
    public static final String POST_LOGOUT_REDIRECT_URI = "http://127.0.0.1:8080/logout";

    // RedisTokenStore prefix
    public static final String REDIS_STORE_PREFIX = "oauth:access:";

    // user_id
    public static final String USER_ID = "user_id";

    // user_name
    public static final String USER_NAME = "user_name";

    // Bad credentials
    public static final String BAD_CREDENTIALS = "Bad credentials";

    // Bad Password
    public static final String BAD_PASSWORD = "密码错误";

    // Invalid Token
    public static final String INVALID_TOKEN = "invalid token";

    // Default salt
    public static final String DEFAULT_SALT = "123456";

    public static final int JWT_EXP_TIME = 3600;

    public static final String JWT_SUB = "sub";

    public static final String JWT_EXP = "exp";

    public static final String JWT_UID = "uid";

    public static final String JWT_PERMISSIONS = "permissions";

}
