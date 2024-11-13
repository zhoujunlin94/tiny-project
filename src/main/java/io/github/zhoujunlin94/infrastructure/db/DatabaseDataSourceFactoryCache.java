package io.github.zhoujunlin94.infrastructure.db;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Db;
import cn.hutool.db.ds.DSFactory;
import cn.hutool.setting.Setting;
import io.github.zhoujunlin94.core.SettingContext;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhoujunlin
 * @date 2024-08-27-14:48
 */
public class DatabaseDataSourceFactoryCache {

    private static final Map<String, DSFactory> DS_FACTORY_CACHE = new ConcurrentHashMap<>();
    private static final String SUFFIX = "-ds-factory";

    static {
        Setting dbSetting = SettingContext.getSetting("db");
        for (String group : dbSetting.getGroups()) {
            Setting dataSourceSetting = dbSetting.getSetting(group);
            DSFactory dsFactory = DSFactory.create(dataSourceSetting);
            DS_FACTORY_CACHE.put(makeKey(group), dsFactory);
        }
    }

    private static String makeKey(String group) {
        return StrUtil.addSuffixIfNot(group, SUFFIX);
    }

    public static DSFactory get(String group) {
        return DS_FACTORY_CACHE.get(makeKey(group));
    }

    public static DataSource getDataSource(String group) {
        return get(group).getDataSource();
    }

    public static Db getDb(String group) {
        return Db.use(getDataSource(group));
    }

}
