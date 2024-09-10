package io.github.zhoujunlin94.job;

import cn.hutool.core.lang.Console;

/**
 * @author zhoujunlin
 * @date 2024-09-10-15:19
 */
public class TestJob {

    public void test1() {
        Console.log(Thread.currentThread().getName() + "-> TestJob.test1");
    }

    public void test2() {
        Console.log(Thread.currentThread().getName() + "-> TestJob.test2");
    }

}
