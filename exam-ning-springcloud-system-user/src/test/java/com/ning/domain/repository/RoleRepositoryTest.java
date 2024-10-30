package com.ning.domain.repository;

import com.ning.domain.entity.Role;
import com.ning.domain.types.RoleId;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void find() {
    }

    @Test
    void save() {
        RoleId roleId = new RoleId(null);
        Role role = new Role(roleId, "管理员", "ADMIN", 1, 0);
        role = roleRepository.save(role);
        log.info("role: {}.", role);
    }

    @Test
    void remove() {
    }

    @Test
    void findAll() {
    }
}