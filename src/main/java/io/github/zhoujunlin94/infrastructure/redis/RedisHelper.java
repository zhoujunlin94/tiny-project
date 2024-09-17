package io.github.zhoujunlin94.infrastructure.redis;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.nosql.redis.RedisDS;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import io.github.zhoujunlin94.common.BeanFactory;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.params.SetParams;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoujunlin
 * @date 2024-09-10-14:10
 */
@Slf4j
public class RedisHelper {

    private final Jedis jedis;

    public RedisHelper(String groupName) {
        this(BeanFactory.getRedisDs(groupName));
    }

    public RedisHelper(RedisDS redisDS) {
        this.jedis = redisDS.getJedis();
    }

    public Object execute(String script, List<String> keys, List<String> args) {
        return this.jedis.eval(script, keys, args);
    }

    public Long del(String... keys) {
        return this.jedis.del(keys);
    }

    public Long del(String key) {
        return this.jedis.del(key);
    }

    public Long deleteByPattern(String pattern) {
        Set<String> keys = this.jedis.keys(pattern);
        if (CollUtil.isEmpty(keys)) {
            return 0L;
        }
        return this.del(keys.toArray(new String[]{}));
    }

    public Boolean exists(String key) {
        return this.jedis.exists(key);
    }

    public Long expire(String key, Long expireTime, TimeUnit timeUnit) {
        return this.jedis.expire(key, timeUnit.toSeconds(expireTime));
    }

    public Long expireAt(String key, Date date) {
        return this.jedis.expireAt(key, date.getTime() / 1000);
    }

    public Long ttl(String key) {
        return this.jedis.ttl(key);
    }

    public String getStr(String key) {
        return this.jedis.get(key);
    }

    public <T> T getStr(String key, Class<T> clazz) {
        try {
            if (StrUtil.isNotBlank(key)) {
                String value = getStr(key);
                if (StrUtil.isNotBlank(value)) {
                    return JSONObject.parseObject(value, clazz);
                }
            }
        } catch (Exception e) {
            log.error("redis getStr出错啦!", e);
        }
        return null;
    }

    public <T> T getStr(String key, TypeReference<T> typeReference) {
        try {
            if (StrUtil.isNotBlank(key)) {
                String value = getStr(key);
                if (StrUtil.isNotBlank(value)) {
                    return JSONObject.parseObject(value, typeReference.getType());
                }
            }
        } catch (Exception e) {
            log.error("redis getStr出错啦!", e);
        }
        return null;
    }

    public String setStr(String key, String value, long secondsToExpire) {
        return this.jedis.set(key, value, new SetParams().ex(secondsToExpire));
    }

    public String setStr(String key, Object value) {
        return this.jedis.set(key, JSONObject.toJSONString(value));
    }

    public String setStr(String key, Object value, Long expireTime, TimeUnit timeUnit) {
        return setStr(key, JSONObject.toJSONString(value), timeUnit.toSeconds(expireTime));
    }

    public Long setStrIfAbsent(String key, Object value) {
        return this.jedis.setnx(key, JSONObject.toJSONString(value));
    }

    public Long incr(String key) {
        return this.jedis.incr(key);
    }

    public Long incrBy(String key, long step) {
        return this.jedis.incrBy(key, step);
    }

    public Long decr(String key) {
        return this.jedis.decr(key);
    }

    public Long decrBy(String key, long step) {
        return this.jedis.decrBy(key, step);
    }

    public Long deleteHashByKeys(String key, String... hashKeys) {
        return this.jedis.hdel(key, hashKeys);
    }

    public Map<String, String> getHash(String key) {
        return this.jedis.hgetAll(key);
    }

    public String getHash(String key, String hashKey) {
        return this.jedis.hget(key, hashKey);
    }

    public Long getHashLength(String key) {
        return this.jedis.hlen(key);
    }

    public Long hashSet(String key, String hashKey, String value) {
        return this.jedis.hset(key, hashKey, value);
    }

    public Long hashSet(String key, Map<String, String> entries) {
        return this.jedis.hset(key, entries);
    }

    public Long hashIncrBy(String key, String hashKey, Long step) {
        return this.jedis.hincrBy(key, hashKey, step);
    }

    public Long addSet(String key, String... members) {
        return this.jedis.sadd(key, members);
    }

    public Set<String> getSet(String key) {
        return this.jedis.smembers(key);
    }

    public String popSet(String key) {
        return this.jedis.spop(key);
    }

    public Long addZSet(String key, String value, double score) {
        return this.jedis.zadd(key, score, value);
    }

    public Set<String> rangeZSetByScore(String key, double min, double max, int offset, int count) {
        return this.jedis.zrangeByScore(key, min, max, offset, count);
    }

    public Long removeZSet(String key, String... values) {
        return this.jedis.zrem(key, values);
    }

    public Long leftPush(String key, String... values) {
        return this.jedis.lpush(key, values);
    }

    public String leftPop(String key) {
        return this.jedis.lpop(key);
    }

    public String rightPopList(String key) {
        return this.jedis.rpop(key);
    }

    public Long publish(String channel, String msg) {
        return this.jedis.publish(channel, msg);
    }

    public void subscribe(JedisPubSub pubSub, String... channels) {
        this.jedis.subscribe(pubSub, channels);
    }

}
