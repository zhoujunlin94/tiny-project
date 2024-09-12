package io.github.zhoujunlin94.infrastructure.webserver;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.lang.Singleton;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhoujunlin
 * @date 2024-09-12-10:57
 */
public class RequestMethodExecutor {

    private static final Map<String, Map<String, RequestMethodMeta>> REQUEST_METHOD_META_MAP = new ConcurrentHashMap<>();

    public static void initRequestMethodMetaMap(String packageName) {
        Set<Class<?>> classSet = ClassUtil.scanPackageByAnnotation(packageName, Endpoint.class);
        classSet.forEach(clazz -> {
            String path = AnnotationUtil.getAnnotation(clazz, Endpoint.class).path();
            for (Method publicMethod : ClassUtil.getPublicMethods(clazz)) {
                if (AnnotationUtil.hasAnnotation(publicMethod, RequestMethod.class)) {
                    RequestMethod requestMethod = AnnotationUtil.getAnnotation(publicMethod, RequestMethod.class);
                    String method = requestMethod.method().toUpperCase();
                    String methodPath = StrUtil.removeSuffix(path, "/") + StrUtil.addPrefixIfNot(requestMethod.path(), "/");
                    if (REQUEST_METHOD_META_MAP.containsKey(methodPath)) {
                        if (REQUEST_METHOD_META_MAP.get(methodPath).containsKey(method)) {
                            throw new RuntimeException(method + " " + methodPath + "已存在");
                        }
                    }
                    RequestMethodMeta requestMethodMeta = new RequestMethodMeta().setEndpointObj(Singleton.get(clazz)).setRequestMethod(method).setPublicMethod(publicMethod);
                    REQUEST_METHOD_META_MAP.put(methodPath, MapUtil.of(method, requestMethodMeta));
                }
            }
        });
    }


    public static Object execute(String requestMethod, String path, HttpServerRequest request, HttpServerResponse response) {
        if (!REQUEST_METHOD_META_MAP.getOrDefault(path, MapUtil.empty()).containsKey(requestMethod.toUpperCase())) {
            response.send404("404 NOT FOUND!");
            return null;
        }
        return REQUEST_METHOD_META_MAP.get(path).get(requestMethod).invoke(request, response);
    }

}
