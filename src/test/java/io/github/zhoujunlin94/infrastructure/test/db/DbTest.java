package io.github.zhoujunlin94.infrastructure.test.db;

import cn.hutool.core.lang.Console;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import io.github.zhoujunlin94.infrastructure.db.DataSourceFactoryCache;
import lombok.SneakyThrows;

import java.util.List;

/**
 * @author zhoujunlin
 * @date 2024-09-10-11:11
 */
public class DbTest {

    @SneakyThrows
    public static void main(String[] args) {
        Db db = DataSourceFactoryCache.getDb("datasource-default");
        List<Entity> entityList = db.find(Entity.create("t_order").set("order_token", "like ORDER%"));
        Console.log(entityList);
    }

}
