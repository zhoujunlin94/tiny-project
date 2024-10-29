package io.github.zhoujunlin94.job;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhoujunlin
 * @date 2024-09-10-15:19
 */
@Slf4j
public class TestJob {

    public void test1() {
        log.warn(Thread.currentThread().getName() + "-> TestJob.test1");
    }

    public void test2() {
        log.warn(Thread.currentThread().getName() + "-> TestJob.test2");
    }

}
