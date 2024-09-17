package io.github.zhoujunlin94.infrastructure.test.redis;

import io.github.zhoujunlin94.common.BeanFactory;
import io.github.zhoujunlin94.infrastructure.redis.RedisHelper;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.JedisPubSub;

/**
 * @author zhoujunlin
 * @date 2024/9/17 21:39
 */
@Slf4j
public class RedisSubscribeTest {

    public static void main(String[] args) {
        RedisHelper redisHelper = BeanFactory.getRedisHelper();

        // 启动多个实例测试广播消费
        redisHelper.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                log.warn("onMessage:{}, message:{}", channel, message);
            }

            @Override
            public void onSubscribe(String channel, int subscribedChannels) {
                log.warn("onSubscribe:{}, subscribedChannels:{}", channel, subscribedChannels);
            }

        }, "testPubSub");

    }

}
