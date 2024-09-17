package io.github.zhoujunlin94.infrastructure.redis;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhoujl
 * @date 2021/3/5 14:52
 */
@Data
@Accessors(chain = true)
public class TaskItem {

    private String requestId;

    private String id;

    private String handlerName;

    private Object msg;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}
