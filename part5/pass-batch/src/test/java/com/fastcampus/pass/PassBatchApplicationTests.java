package com.fastcampus.pass;

import com.fastcampus.pass.config.TestBatchConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles("test")
@ContextConfiguration(classes = {TestBatchConfig.class})
@SpringBootTest
class PassBatchApplicationTests {

    @Test
    void contextLoads() {
    }

}
