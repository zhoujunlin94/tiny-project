package io.github.zhoujunlin94.core;

import cn.hutool.core.lang.Singleton;
import cn.hutool.db.Db;
import cn.hutool.db.nosql.redis.RedisDS;
import io.github.zhoujunlin94.infrastructure.db.DatabaseDataSourceFactoryCache;
import io.github.zhoujunlin94.infrastructure.redis.RedisDataSourceFactoryCache;
import io.github.zhoujunlin94.infrastructure.redis.RedisHelper;

/**
 * @author zhoujunlin
 * @date 2024/9/17 21:46
 */
public class BeanFactory {

    public static Db getDb() {
        return getDb("datasource-default");
    }

    public static Db getDb(String group) {
        return DatabaseDataSourceFactoryCache.getDb(group);
    }

    public static RedisDS getRedisDs(String groupName) {
        return RedisDataSourceFactoryCache.get(groupName);
    }

    public static RedisHelper getRedisHelper() {
        return getRedisHelper("custom");
    }

    public static RedisHelper getRedisHelper(String groupName) {
        return new RedisHelper(getRedisDs(groupName));
    }

    public static <T> T getBean(Class<T> clazz) {
        return Singleton.get(clazz);
    }

}
