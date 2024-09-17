package io.github.zhoujunlin94.infrastructure.redis;

import cn.hutool.core.io.FileUtil;

/**
 * @author zhoujunlin
 * @date 2024/9/17 22:19
 */
public class RedisConstant {

    public static final String QUEUE_LUA = FileUtil.readUtf8String("classpath:scripts/queue.lua");

}
