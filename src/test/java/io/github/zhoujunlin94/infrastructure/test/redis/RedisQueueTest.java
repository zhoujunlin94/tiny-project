package io.github.zhoujunlin94.infrastructure.test.redis;

import io.github.zhoujunlin94.core.BeanFactory;
import io.github.zhoujunlin94.infrastructure.redis.RedisDelayQueue;

import java.util.concurrent.TimeUnit;

/**
 * @author zhoujunlin
 * @date 2024/9/17 22:21
 */
public class RedisQueueTest {

    public static void main(String[] args) {
        RedisDelayQueue redisDelayQueue = BeanFactory.getBean(RedisDelayQueue.class);
        //验证redis 生产消费模式  起多台服务测试  - 一条消息只有一台机器消费（竞争关系）
        //需要立即被消费
        redisDelayQueue.delay("defaultQueueMsgHandler", "provider demo msg1-1", 0, TimeUnit.SECONDS);
        //延迟5秒消费
        redisDelayQueue.delay("defaultQueueMsgHandler", "provider demo msg2-1", 5, TimeUnit.SECONDS);
        redisDelayQueue.delay("defaultQueueMsgHandler", "provider demo msg3-1", 30, TimeUnit.SECONDS);
    }

}
