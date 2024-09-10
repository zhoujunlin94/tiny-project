package io.github.zhoujunlin94.infrastructure.test.webserver;

import cn.hutool.http.ContentType;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * @author zhoujunlin
 * @date 2024-09-10-14:55
 */
public class WebServerTest {

    public static void main(String[] args) {
        HttpUtil.createServer(8080).addAction("/test", ((request, response) -> {
            System.out.println(request.getMethod());
            response.write(new JSONObject().fluentPut("status", 0).toJSONString(), ContentType.JSON.toString());
        })).start();
    }

}
