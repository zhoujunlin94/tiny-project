package io.github.zhoujunlin94.infrastructure.webserver;

import cn.hutool.core.date.StopWatch;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;
import cn.hutool.http.server.action.Action;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.io.IOException;

/**
 * @author zhoujunlin
 * @date 2024-09-11-11:02
 */
@Slf4j
public class DispatcherAction implements Action {

    public DispatcherAction(String packageName) {
        RequestMethodExecutor.initRequestMethodMetaMap(packageName);
    }

    @Override
    public void doAction(HttpServerRequest request, HttpServerResponse response) throws IOException {
        String fastSimpleUUID = IdUtil.fastSimpleUUID();
        MDC.put("X-REQUEST-ID", fastSimpleUUID);
        response.addHeader("X-REQUEST-ID", fastSimpleUUID);

        String requestMethod = request.getMethod();
        String path = request.getPath();
        StopWatch stopWatch = new StopWatch();
        try {
            log.warn("开始请求{} {}", requestMethod, path);
            String queryString = request.getQuery();
            if (StrUtil.isNotBlank(queryString)) {
                log.warn("入参queryString:\n{}", queryString);
            }
            String requestBody = request.getBody();
            if (StrUtil.isNotBlank(requestBody)) {
                log.warn("入参RequestBody:\n{}", requestBody);
            }
            stopWatch.start();
            RequestMethodExecutor.execute(requestMethod, path, request, response);
        } finally {
            stopWatch.stop();
            log.warn("结束请求{} {}, 耗时:{}ms", requestMethod, path, stopWatch.getTotalTimeMillis());
            MDC.remove("X-REQUEST-ID");
        }
    }


}
