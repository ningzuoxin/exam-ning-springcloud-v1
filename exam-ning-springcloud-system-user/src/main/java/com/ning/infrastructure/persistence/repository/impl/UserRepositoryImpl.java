package com.ning.infrastructure.persistence.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ning.domain.entity.User;
import com.ning.domain.repository.UserRepository;
import com.ning.domain.types.Username;
import com.ning.infrastructure.persistence.converter.UserConverter;
import com.ning.infrastructure.persistence.dao.UserDao;
import com.ning.infrastructure.persistence.model.UserDO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * 用户仓储实现类
 *
 * @author zuoxin.ning
 * @since 2024-03-24 08:00
 */
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserDao userDao;
    private final UserConverter userConverter;

    /**
     * 查询用户
     *
     * @param username 用户名
     * @return 用户
     */
    @Override
    public User find(Username username) {
        UserDO userDO = this.selectByUsername(username);
        return userConverter.toEntity(userDO);
    }

    /**
     * 保存用户
     *
     * @param user 用户
     * @return 用户
     */
    @Override
    public User save(User user) {
        UserDO userDO = userConverter.toDO(user);
        int res = userDao.insert(userDO);
        return user;
    }

    /**
     * 根据用户名查询
     *
     * @param username 用户名
     * @return 用户
     */
    private UserDO selectByUsername(Username username) {
        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username.getValue());
        wrapper.eq("is_deleted", 0);
        return userDao.selectOne(wrapper);
    }

}
