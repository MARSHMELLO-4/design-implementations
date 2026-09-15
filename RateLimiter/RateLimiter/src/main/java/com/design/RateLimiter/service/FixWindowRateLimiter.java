package com.design.RateLimiter.service;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class FixWindowRateLimiter implements RateLimiter{

    private final RedisTemplate<String, String> redisTemplate;
    private final int limit = 3;
    private final int windowSeconds = 20;

    public FixWindowRateLimiter(RedisTemplate<String, String> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean allow(String userId) {
        String key = "rate-limit : " + userId;

        System.out.println("Key : " + key);

        Long count = redisTemplate
                .opsForValue()
                .increment(key);

        System.out.println("Count for key : " + key + " : " + count);

        if(count == 1){
            redisTemplate.expire(
                    key,
                    windowSeconds,
                    TimeUnit.SECONDS
            );
        }

        return count <= limit;
    }
}
