package com.ning.domain.repository;

import com.ning.domain.entity.User;
import com.ning.domain.types.UserId;
import com.ning.domain.types.Username;
import com.ning.infrastructure.common.PageWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void find() {
        User user = userRepository.find(new Username("admin"));
        log.info("user: {}.", user);
    }

    @Test
    void saveForNew() {
        UserId userId = new UserId(null);
        Username username = new Username("san.zhang");
        User user = new User(userId, username, "123456", "s123", "zhangsan", 2, "13101310131");
        user = userRepository.save(user);
        log.info("user: {}.", user);
    }

    @Test
    void saveForUpdate() {
        User user = userRepository.find(new UserId(123L));
        user.setNickname("si.li");
        user.setGender(2);
        user = userRepository.save(user);
        log.info("user: {}.", user);
    }

    @Test
    void findAll() {
        List<User> userList = userRepository.findAll();
        log.info("userList: {}.", userList);
    }

    @Test
    void findByPage() {
        String keyword = "a";
        int pageNum = 0;
        int pageSize = 10;

        PageWrapper<User> pageUser = userRepository.findByPage(keyword, pageNum, pageSize);
        log.info("pageUser: {}.", pageUser);
    }

    @Test
    void remove() {
    }

    @Test
    void testFind() {
        User user = userRepository.find(new UserId(123L));
        log.info("user: {}.", user);
    }

}