package io.github.zhoujunlin94.infrastructure.redis;

import cn.hutool.db.nosql.redis.RedisDS;
import io.github.zhoujunlin94.common.SettingContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

/**
 * @author zhoujunlin
 * @date 2024-09-10-14:10
 */
public class RedisHelper {

    private final Jedis jedis;

    public RedisHelper(String settingName, String groupName) {
        RedisDS redisDataSource = RedisDS.create(SettingContext.getSetting(settingName), groupName);
        this.jedis = redisDataSource.getJedis();
    }

    public String set(String key, String value, long secondsToExpire) {
        return this.jedis.set(key, value, new SetParams().ex(secondsToExpire));
    }

    public String get(String key) {
        return this.jedis.get(key);
    }

}
