package io.github.zhoujunlin94.infrastructure.redis;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.IdUtil;
import io.github.zhoujunlin94.core.BeanFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoujl
 * @date 2021/3/5 14:51
 * @desc
 */
@Slf4j
public class RedisDelayQueue {

    private static final String QUEUE_KEY = "queue:msg";

    private final RedisHelper redisHelper;

    public RedisDelayQueue() {
        this.redisHelper = BeanFactory.getRedisHelper();
        ThreadUtil.newExecutor().execute(this::start);
        log.warn("redis queue started...");
    }

    /**
     * 向队列中添加消息
     *
     * @param handlerName 处理器名字
     * @param msg         消息内容
     * @param delayTime   延迟执行时间
     * @param timeUnit    延迟时间单位
     */
    public void delay(String handlerName, Object msg, long delayTime, TimeUnit timeUnit) {
        TaskItem taskItem = new TaskItem().setId(IdUtil.fastSimpleUUID()).setHandlerName(handlerName).setMsg(msg);
        redisHelper.addZSet(QUEUE_KEY, taskItem.toString(), (System.currentTimeMillis() + timeUnit.toMillis(delayTime)));
    }


    public void start() {
        try {
            String luaStr = RedisConstant.QUEUE_LUA;
            while (!Thread.interrupted()) {
                String nowStamp = System.currentTimeMillis() + "";
                List<String> retList = (List<String>) redisHelper.execute(luaStr, Collections.singletonList(QUEUE_KEY), Collections.singletonList(nowStamp));
                if (CollUtil.isEmpty(retList)) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        break;
                    }
                    continue;
                }

                retList.forEach(msg -> {
                    log.warn("获取延迟消息:{}", msg);
                });
            }
        } catch (Exception e) {
            log.error("获取延迟消息出错", e);
        }
    }

}