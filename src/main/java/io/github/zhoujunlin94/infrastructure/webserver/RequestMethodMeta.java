package io.github.zhoujunlin94.infrastructure.webserver;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;
import lombok.Data;
import lombok.experimental.Accessors;

import java.lang.reflect.Method;

/**
 * @author zhoujunlin
 * @date 2024-09-12-10:53
 */
@Data
@Accessors(chain = true)
public class RequestMethodMeta {

    private String requestMethod;

    private Object endpointObj;

    private Method publicMethod;


    public Object invoke(HttpServerRequest request, HttpServerResponse response) {
        return ReflectUtil.invoke(endpointObj, publicMethod, request, response);
    }

}
