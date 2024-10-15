package com.ning.infrastructure.persistence.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    /**
     * 查询全部用户
     *
     * @return 全部用户
     */
    @Override
    public List<User> findAll() {
        List<UserDO> userDOList = userDao.selectList(null);
        return userConverter.toEntityList(userDOList);
    }

    /**
     * 分页查询用户列表
     *
     * @param keyword  关键词
     * @param pageNum  当前页
     * @param pageSize 当前页条数
     * @return 用户列表
     */
    @Override
    public PageWrapper<User> findByPage(String keyword, Integer pageNum, Integer pageSize) {
        // 分页对象
        IPage<UserDO> iPage = new Page<>(pageNum, pageSize);

        // 查询对象
        LambdaQueryWrapper<UserDO> wrapper = new QueryWrapper<UserDO>().lambda();
        wrapper.eq(UserDO::getIsDeleted, 0);
        if (StrUtil.isNotEmpty(keyword)) {
            wrapper.like(UserDO::getNickname, keyword).or()
                    .like(UserDO::getUsername, keyword).or()
                    .like(UserDO::getEmail, keyword).or()
                    .like(UserDO::getPhoneNumber, keyword);
        }
        wrapper.orderByDesc(UserDO::getCreateTime);

        IPage<UserDO> userDOIPage = userDao.selectPage(iPage, wrapper);
        return PageWrapper.<User>builder()
                .total(userDOIPage.getTotal())
                .pageNum(pageNum)
                .pageSize(pageSize)
                .data(userConverter.toEntityList(userDOIPage.getRecords()))
                .build();
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
