package io.github.zhoujunlin94.infrastructure.test.guava;

import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.*;
import com.google.common.eventbus.EventBus;
import com.google.common.primitives.Ints;
import lombok.SneakyThrows;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoujunlin
 * @date 2025-05-23-13:10
 */
public class GuavaTest {

    @Test
    public void testEventBus() {
        EventBus eventBus = new EventBus();
        eventBus.register(new OrderEventListener());
        eventBus.post(new OrderCreatedEvent("ssssssss"));
    }

    @Test
    @SneakyThrows
    public void testRetry() {
        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                .retryIfResult(Boolean.FALSE::equals) // 返回false就重试
                .retryIfExceptionOfType(IOException.class) //IO异常重试
                .withStopStrategy(StopStrategies.stopAfterAttempt(3)) // 最多3次
                .build();

        // 执行
        Boolean result = retryer.call(() -> {
            System.out.println("正在调用接口...");
            double random = Math.random();
            System.out.println("随机：" + random);
            if (random >= 0.9) {
                return false;
            }
            if (random <= 0.2) {
                throw new IOException("故意抛异常");
            }
            return true;
        });

        System.out.println(result);
    }


    @Test
    @SneakyThrows
    public void testCache() {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder().maximumSize(100)
                .refreshAfterWrite(5, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        System.out.println(key + "加载。。。");
                        return "zhou";
                    }
                });
        String name = cache.get("name");
        System.out.println(name);

        System.out.println("---------------");
        name = cache.get("name");
        System.out.println(name);

        TimeUnit.SECONDS.sleep(5);

        System.out.println("---------------");
        name = cache.get("name");
        System.out.println(name);
    }


    @Test
    public void testRange() {
        // 闭区间
        Range<Integer> closed = Range.closed(60, 100);
        System.out.println(closed.contains(60));
        // 开区间
        Range<Integer> open = Range.open(80, 100);
        System.out.println(open.contains(80));
    }

    @Test
    public void testInts() {
        int[] intArr = {1, 2, 3, 4};
        List<Integer> ints = Ints.asList(intArr);
        System.out.println(Ints.max(intArr));
        System.out.println(Ints.contains(intArr, 2));
    }


    @Test
    public void testBitMap() {
        BiMap<Integer, String> roleMap = HashBiMap.create();
        roleMap.put(1, "admin");
        roleMap.put(2, "root");
        System.out.println(roleMap.get(1));
        System.out.println(roleMap.inverse().get("admin"));
    }

    @Test
    public void testMultiMap() {
        Multimap<String, String> multiMap = ArrayListMultimap.create();
        multiMap.put("name", "zhou");
        multiMap.put("name", "jun");
        multiMap.put("name", "lin");
        multiMap.put("age", "30");
        System.out.println(multiMap.get("name"));
    }

}
