package io.github.zhoujunlin94.infrastructure.test.webserver;

import cn.hutool.core.lang.Console;
import cn.hutool.http.ContentType;
import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;
import com.alibaba.fastjson.JSONObject;
import io.github.zhoujunlin94.infrastructure.webserver.Endpoint;
import io.github.zhoujunlin94.infrastructure.webserver.RequestMethod;

/**
 * @author zhoujunlin
 * @date 2024-09-12-10:15
 */
@Endpoint(path = "/api/v1/")
public class TestEndpoint {


    @RequestMethod(path = "/test1")
    public void test1(HttpServerRequest request, HttpServerResponse response) {
        response.write(new JSONObject().fluentPut("status", 0).fluentPut("msg", "你好").toJSONString(), ContentType.JSON.toString());
    }

    @RequestMethod(path = "/test2", method = "POST")
    public void test2(HttpServerRequest request, HttpServerResponse response) {
        Console.log(request, response);
    }

    // @RequestMethod(path = "/test2", method = "POST")
    public void test3(HttpServerRequest request, HttpServerResponse response) {
        Console.log(request, response);
    }

    public void test4(HttpServerRequest request, HttpServerResponse response) {
        Console.log("never call this method");
    }

}
