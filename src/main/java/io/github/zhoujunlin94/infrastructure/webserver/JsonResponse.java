package io.github.zhoujunlin94.infrastructure.webserver;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhoujunlin
 * @date 2024-09-12-13:06
 */
@Data
@Accessors(chain = true)
public class JsonResponse {

    private int status;
    private Object result;
    private String message;

    public static JsonResponse ok(Object result) {
        return new JsonResponse().setStatus(0).setResult(result);
    }

    public static JsonResponse fail(int status, String message) {
        return new JsonResponse().setStatus(status).setMessage(message);
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}
