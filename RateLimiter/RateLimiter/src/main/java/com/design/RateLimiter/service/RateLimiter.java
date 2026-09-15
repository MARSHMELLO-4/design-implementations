package com.design.RateLimiter.service;

import org.springframework.stereotype.Service;

@Service
public interface RateLimiter {

    boolean allow(String userId);

}
