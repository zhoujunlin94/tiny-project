package io.github.zhoujunlin94.core;

import cn.hutool.core.lang.Singleton;
import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.Setting;
import cn.hutool.setting.SettingUtil;
import cn.hutool.setting.profile.GlobalProfile;
import cn.hutool.setting.profile.Profile;

/**
 * @author zhoujunlin
 * @date 2024-09-10-10:50
 */
public class SettingContext {

    private static final Setting APPLICATION_SETTING = SettingUtil.get("application");

    private static final Profile GLOBAL_PROFILE = Singleton.get("SettingContext", () -> {
        String profile = APPLICATION_SETTING.get("application.profile");
        return GlobalProfile.setProfile(StrUtil.blankToDefault(profile, "dev")).setUseVar(true);
    });

    public static Setting getSetting(String settingName) {
        return APPLICATION_SETTING.addSetting(GLOBAL_PROFILE.getSetting(settingName));
    }

    public static Setting getSetting(String settingName, String groupName) {
        return APPLICATION_SETTING.addSetting(GLOBAL_PROFILE.getSetting(settingName).getSetting(groupName));
    }

}
