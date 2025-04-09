package com.project.ShareBook.util;

import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RedisUtil {
    private final RedisTemplate<String, String> redisTemplate;

    @Transactional
    public void setValues(String key, String data) {
        redisTemplate.opsForValue().set(key, data);
    }

    @Transactional
    public void setValues(String key, String data, Long timeout) {
        redisTemplate.opsForValue().set(key, data, timeout, TimeUnit.MILLISECONDS);
    }

    public String getValues(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Transactional
    public void deleteValues(String key) {
        redisTemplate.delete(key);
    }

    public boolean checkExistsValue(String value) {
        return redisTemplate.hasKey(value);
    }
}
