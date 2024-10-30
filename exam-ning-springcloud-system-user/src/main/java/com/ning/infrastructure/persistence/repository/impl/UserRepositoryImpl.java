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
import com.ning.infrastructure.common.model.PageWrapper;
import com.ning.infrastructure.persistence.converter.UserConverter;
import com.ning.infrastructure.persistence.dao.UserDao;
import com.ning.infrastructure.persistence.model.UserDO;
import com.ning.infrastructure.utils.SnowFlake;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    private final UserConverter userConverter = UserConverter.INSTANCE;

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
        if (Objects.isNull(userDO.getUid())) {
            userDO.setUid(SnowFlake.ID.nextId());
            userDao.insert(userDO);
            return userConverter.toEntity(userDO);
        } else {
            Optional<UserDO> userDOOpt = this.findByUid(user.getId().getValue());
            if (!userDOOpt.isPresent()) {
                throw new IllegalArgumentException("User not exits, id: " + user.getId().getValue());
            }

            UserDO dbUserDO = userDOOpt.get();
            userConverter.updateDO(dbUserDO, userDO);
            userDao.updateById(dbUserDO);
            return userConverter.toEntity(dbUserDO);
        }
    }

    /**
     * 查询全部用户
     *
     * @return 全部用户
     */
    @Override
    public List<User> findAll() {
        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        wrapper.eq("is_deleted", 0);
        List<UserDO> userDOList = userDao.selectList(wrapper);
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

    /**
     * 删除用户
     *
     * @param userId 用户 ID
     * @return 是否操作成功
     */
    @Override
    public boolean remove(UserId userId) {
        Optional<UserDO> userDOOpt = this.findByUid(userId.getValue());
        if (!userDOOpt.isPresent()) {
            return true;
        }

        UserDO userDO = userDOOpt.get();
        userDO.setIsDeleted(1);
        userDao.updateById(userDO);
        return true;
    }

    /**
     * 查询用户
     *
     * @param userId 用户 ID
     * @return 用户
     */
    @Override
    public Optional<User> find(UserId userId) {
        return this.findByUid(userId.getValue()).map(userConverter::toEntity);
    }

    /**
     * 统计用户数
     *
     * @param username 用户名
     * @return 用户数
     */
    @Override
    public long count(Username username) {
        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username.getValue());
        wrapper.eq("is_deleted", 0);
        return userDao.selectCount(wrapper);
    }

    private Optional<UserDO> findByUid(Long uid) {
        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid);
        wrapper.eq("is_deleted", 0);
        return Optional.ofNullable(userDao.selectOne(wrapper));
    }

}
