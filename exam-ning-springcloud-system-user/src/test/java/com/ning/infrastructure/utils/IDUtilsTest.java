package com.ning.infrastructure.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class IDUtilsTest {
    @Test
    public void id() {
        Long id = IDUtils.getId();
        log.info("res: {}.", id);
    }

}