package com.ning.infrastructure.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class IDUtilsTest {

    @Autowired
    private IDUtils idUtils;

    @Test
    public void id() {
        Long id = idUtils.getId("user");
        log.info("res: {}.", id);
    }

}