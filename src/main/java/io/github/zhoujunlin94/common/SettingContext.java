package io.github.zhoujunlin94.common;

import cn.hutool.core.lang.Singleton;
import cn.hutool.setting.Setting;
import cn.hutool.setting.SettingUtil;
import cn.hutool.setting.profile.GlobalProfile;
import cn.hutool.setting.profile.Profile;

/**
 * @author zhoujunlin
 * @date 2024-09-10-10:50
 */
public class SettingContext {

    private static final Profile GLOBAL_PROFILE = Singleton.get("SettingContext", () -> {
        Setting applicationSetting = SettingUtil.get("application");
        String profile = applicationSetting.get("application.profile");
        return GlobalProfile.setProfile(profile).setUseVar(true);
    });

    public static Setting getSetting(String settingName) {
        return GLOBAL_PROFILE.getSetting(settingName);
    }

    public static Setting getSetting(String settingName, String groupName) {
        return GLOBAL_PROFILE.getSetting(settingName).getSetting(groupName);
    }

}
