package com.ning.domain.repository;

import com.ning.domain.entity.User;
import com.ning.domain.types.Username;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    void save() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findByPage() {
    }

    @Test
    void remove() {
    }

    @Test
    void testFind() {
    }
}