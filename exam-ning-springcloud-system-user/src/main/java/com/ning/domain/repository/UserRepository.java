package com.ning.domain.repository;

import com.ning.domain.entity.User;
import com.ning.domain.types.Username;

/**
 * 用户仓储
 *
 * @author zuoxin.ning
 * @since 2024-03-24
 */
public interface UserRepository {

    User find(Username username) throws Exception;

    User save(User user) throws Exception;

}
