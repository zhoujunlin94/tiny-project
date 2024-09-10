package io.github.zhoujunlin94.infrastructure.test.redis;

import io.github.zhoujunlin94.infrastructure.redis.RedisHelper;

/**
 * @author zhoujunlin
 * @date 2024-09-10-14:03
 */
public class RedisTest {

    public static void main(String[] args) {
        RedisHelper redisHelper = new RedisHelper("redis", "custom");

        System.out.println(redisHelper.set("test", "111", 120L));
        System.out.println(redisHelper.get("test"));

    }

}
