package io.github.zhoujunlin94.infrastructure.test.webserver;

import cn.hutool.core.lang.Console;
import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;
import com.alibaba.fastjson.JSONObject;
import io.github.zhoujunlin94.infrastructure.webserver.BaseEndpoint;
import io.github.zhoujunlin94.infrastructure.webserver.Endpoint;
import io.github.zhoujunlin94.infrastructure.webserver.RequestMethod;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhoujunlin
 * @date 2024-09-12-10:15
 */
@Slf4j
@Endpoint(path = "/api/v1/")
public class TestEndpoint extends BaseEndpoint {


    @RequestMethod(path = "/test1")
    public void test1(HttpServerRequest request, HttpServerResponse response) {
        String name = request.getParam("name");
        writeJson(response, "你好," + name);
    }

    @RequestMethod(path = "/test2", method = "POST")
    public void test2(HttpServerRequest request, HttpServerResponse response) {
        String requestBody = request.getBody();
        JSONObject jsonObject = JSONObject.parseObject(requestBody);
        jsonObject.put("server", "hutool");
        writeJson(response, jsonObject);
    }

    // @RequestMethod(path = "/test2", method = "POST")
    public void test3(HttpServerRequest request, HttpServerResponse response) {
        Console.log(request, response);
    }

    public void test4(HttpServerRequest request, HttpServerResponse response) {
        Console.log("never call this method");
    }

}
