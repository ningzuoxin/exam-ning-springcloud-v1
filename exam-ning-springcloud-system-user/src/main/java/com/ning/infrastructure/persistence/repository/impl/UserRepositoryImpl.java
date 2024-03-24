package com.ning.infrastructure.persistence.repository.impl;

import com.ning.domain.entity.User;
import com.ning.domain.repository.UserRepository;
import com.ning.domain.types.Username;
import org.springframework.stereotype.Repository;

/**
 * 用户仓储实现类
 *
 * @author zuoxin.ning
 * @since 2024-03-24
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Override
    public User find(Username username) throws Exception {
        return null;
    }

    @Override
    public User save(User user) throws Exception {
        return null;
    }

}
