package com.ning.domain.repository;

import com.ning.domain.entity.User;
import com.ning.domain.types.Username;

/**
 * 用户仓储
 *
 * @author zuoxin.ning
 * @since 2024-03-24 08:00
 */
public interface UserRepository {

    /**
     * 查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    User find(Username username);

    /**
     * 保存用户
     *
     * @param user 用户
     * @return 用户
     */
    User save(User user);

}
