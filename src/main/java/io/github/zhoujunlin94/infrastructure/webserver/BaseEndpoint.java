package io.github.zhoujunlin94.infrastructure.webserver;

import cn.hutool.http.ContentType;
import cn.hutool.http.server.HttpServerResponse;

/**
 * @author zhoujunlin
 * @date 2024-09-12-13:07
 */
public class BaseEndpoint {

    public void writeJson(HttpServerResponse response, Object result) {
        Object resp = result instanceof JsonResponse ? result : JsonResponse.ok(result);
        writeJson(response, resp.toString());
    }

    public void fail(HttpServerResponse response, int status, String message) {
        JsonResponse jsonResponse = JsonResponse.fail(status, message);
        writeJson(response, jsonResponse.toString());
    }

    private void writeJson(HttpServerResponse response, String json) {
        response.write(json, ContentType.JSON.toString());
    }

}
