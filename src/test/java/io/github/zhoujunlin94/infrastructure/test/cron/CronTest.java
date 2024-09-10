package io.github.zhoujunlin94.infrastructure.test.cron;

import cn.hutool.cron.CronUtil;

/**
 * @author zhoujunlin
 * @date 2024-09-10-15:22
 */
public class CronTest {

    public static void main(String[] args) {
        CronUtil.setCronSetting("cron.setting");
        CronUtil.setMatchSecond(true);
        CronUtil.start();
    }

}
