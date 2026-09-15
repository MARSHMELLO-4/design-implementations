package com.design.RateLimiter.controller;


import com.design.RateLimiter.service.FixWindowRateLimiter;
import com.design.RateLimiter.service.RateLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimiterController {

    private final RateLimiter rateLimiter;

    public RateLimiterController(FixWindowRateLimiter rateLimiter){
        this.rateLimiter = rateLimiter;
    }

    @GetMapping("/api/test/{username}")
    public String getMessage(@PathVariable String username){
        if(!rateLimiter.allow(username)){
            return "Too Many Requests";
        }

        return "Welcome!";
    }

}
