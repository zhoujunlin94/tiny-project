package io.github.zhoujunlin94.infrastructure.test.redis;

import io.github.zhoujunlin94.common.BeanFactory;
import io.github.zhoujunlin94.infrastructure.redis.RedisHelper;
import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @author zhoujunlin
 * @date 2024/9/17 21:37
 */
public class RedisPubTest {

    @SneakyThrows
    public static void main(String[] args) {
        RedisHelper redisHelper = BeanFactory.getRedisHelper();
        for (int i = 0; i < 10; i++) {
            redisHelper.publish("testPubSub", "msg" + i);
            TimeUnit.MILLISECONDS.sleep(500);
        }
    }


}
