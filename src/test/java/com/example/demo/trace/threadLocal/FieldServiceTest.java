package com.example.demo.trace.threadLocal;

import com.example.demo.trace.threadLocal.code.ThreadLocalService;
import com.example.demo.util.Util;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {
    private ThreadLocalService threadLocalService = new ThreadLocalService();

    @Test
    void field() {
        log.info("main start");
        Runnable userA = () -> {
            threadLocalService.logic("userA");
        };
        Runnable userB = () -> {
            threadLocalService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        Util.sleep(100);
        threadB.start();

        Util.sleep(2000);
        log.info("main exit");
    }

}
