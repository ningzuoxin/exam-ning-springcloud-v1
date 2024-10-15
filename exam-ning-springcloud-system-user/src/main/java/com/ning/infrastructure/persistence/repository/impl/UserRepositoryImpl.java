package com.ning.infrastructure.persistence.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ning.domain.entity.User;
import com.ning.domain.repository.UserRepository;
import com.ning.domain.types.UserId;
import com.ning.domain.types.Username;
import com.ning.infrastructure.common.PageWrapper;
import com.ning.infrastructure.persistence.converter.UserConverter;
import com.ning.infrastructure.persistence.dao.UserDao;
import com.ning.infrastructure.persistence.model.UserDO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username.getValue());
        wrapper.eq("is_deleted", 0);
        UserDO userDO = userDao.selectOne(wrapper);
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

    @Override
    public List<User> findAll() {
        List<UserDO> userDOList = userDao.selectList(null);
        return null;
    }

    @Override
    public PageWrapper<User> findByPage(String keyword, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public boolean remove(UserId userId) {
        return false;
    }

    /**
     * 查询用户
     *
     * @param userId 用户 ID
     * @return 用户
     */
    @Override
    public User find(UserId userId) {
        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", userId.getValue());
        wrapper.eq("is_deleted", 0);
        UserDO userDO = userDao.selectOne(wrapper);
        return userConverter.toEntity(userDO);
    }

}
