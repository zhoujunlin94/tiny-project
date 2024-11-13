package io.github.zhoujunlin94.infrastructure.test.redis;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;
import io.github.zhoujunlin94.core.BeanFactory;
import io.github.zhoujunlin94.infrastructure.redis.RedisHelper;

import java.util.Date;

/**
 * @author zhoujunlin
 * @date 2024-09-10-14:03
 */
public class RedisTest {

    public static void main(String[] args) {
        RedisHelper redisHelper = BeanFactory.getRedisHelper();

        Console.log(redisHelper.setStr("test", "111", 120L));
        Console.log(redisHelper.getStr("test"));

        redisHelper.setStr("test2", "222");
        redisHelper.expireAt("test2", DateUtil.offsetHour(new Date(), 1));

        System.out.println(redisHelper.ttl("test2"));

    }

}
