package io.github.zhoujunlin94.infrastructure.test.webserver;

import cn.hutool.http.HttpUtil;
import io.github.zhoujunlin94.infrastructure.webserver.DispatcherAction;

/**
 * @author zhoujunlin
 * @date 2024-09-10-14:55
 */
public class WebServerTest {

    public static void main(String[] args) {
        HttpUtil.createServer(8080).addAction("/", new DispatcherAction("io.github.zhoujunlin94.infrastructure.test.webserver")).start();
    }

}
