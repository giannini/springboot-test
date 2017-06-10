package com.gia.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by Fenglin on 2017/6/10.
 */
@Component
public class RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static String REDIS_KEY_PREFIX = "dt_log_agent_";

    public void write(String uuid, long value, long time) {
        redisTemplate.opsForValue().set(REDIS_KEY_PREFIX + uuid + "_" + time, String.valueOf(value),
                60, TimeUnit.MINUTES);
    }

    public long read(String uuid) {
        Set<String> keys = redisTemplate.keys(REDIS_KEY_PREFIX + uuid + "_" + "*");
        System.out.println(JSON.toJSONString(keys));
        long total = 0;
        for (String key : keys) {
            String val = redisTemplate.opsForValue().get(key);
            total += Long.valueOf(val);
        }

        return total;
    }


}
