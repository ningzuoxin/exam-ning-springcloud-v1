package com.ning.domain.repository;

import com.ning.domain.entity.User;
import com.ning.domain.types.UserId;
import com.ning.domain.types.Username;
import com.ning.infrastructure.common.model.PageWrapper;

import java.util.List;
import java.util.Optional;

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

    /**
     * 查询全部用户
     *
     * @return 全部用户
     */
    List<User> findAll();

    /**
     * 分页查询用户列表
     *
     * @param keyword  关键词
     * @param pageNum  当前页
     * @param pageSize 当前页条数
     * @return 用户列表
     */
    PageWrapper<User> findByPage(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 删除用户
     *
     * @param userId 用户 ID
     * @return 是否操作成功
     */
    boolean remove(UserId userId);

    /**
     * 查询用户
     *
     * @param userId 用户 ID
     * @return 用户
     */
    Optional<User> find(UserId userId);

    /**
     * 统计用户数
     *
     * @param username 用户名
     * @return 用户数
     */
    long count(Username username);

}
