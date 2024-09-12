package io.github.zhoujunlin94.infrastructure.webserver;

import cn.hutool.core.date.StopWatch;
import cn.hutool.core.lang.Console;
import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;
import cn.hutool.http.server.action.Action;

import java.io.IOException;

/**
 * @author zhoujunlin
 * @date 2024-09-11-11:02
 */
public class DispatcherAction implements Action {

    public DispatcherAction(String packageName) {
        RequestMethodExecutor.initRequestMethodMetaMap(packageName);
    }

    @Override
    public void doAction(HttpServerRequest request, HttpServerResponse response) throws IOException {
        String requestMethod = request.getMethod();
        String path = request.getPath();
        Console.log("开始请求{} {}", requestMethod, path);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        if (!RequestMethodExecutor.contain(requestMethod, path)) {
            response.send404("404 NOT FOUND!");
            printEnd(stopWatch, requestMethod, path);
            return;
        }

        RequestMethodExecutor.execute(requestMethod, path, request, response);

        printEnd(stopWatch, requestMethod, path);
    }

    private void printEnd(StopWatch stopWatch, String requestMethod, String path) {
        stopWatch.stop();
        Console.log("结束请求{} {}, 耗时:{}ms", requestMethod, path, stopWatch.getTotalTimeMillis());
    }

}
