package io.github.zhoujunlin94.infrastructure.redis;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.nosql.redis.RedisDS;
import cn.hutool.setting.Setting;
import io.github.zhoujunlin94.common.SettingContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhoujunlin
 * @date 2024/9/17 21:57
 */
public class RedisDataSourceFactoryCache {

    private static final Map<String, RedisDS> REDIS_DS_FACTORY_CACHE = new ConcurrentHashMap<>();
    private static final String SUFFIX = "-redis-ds-factory";

    static {
        Setting redisSetting = SettingContext.getSetting("redis");
        for (String group : redisSetting.getGroups()) {
            RedisDS redisDS = RedisDS.create(redisSetting, group);
            REDIS_DS_FACTORY_CACHE.put(makeKey(group), redisDS);
        }
    }

    private static String makeKey(String group) {
        return StrUtil.addSuffixIfNot(group, SUFFIX);
    }

    public static RedisDS get(String group) {
        return REDIS_DS_FACTORY_CACHE.get(makeKey(group));
    }

}
