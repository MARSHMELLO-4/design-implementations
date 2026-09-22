package com.design.UrlShortner.controller;


import com.design.UrlShortner.service.UrlService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/url")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService){
        this.urlService = urlService;
    }


    @PostMapping("/{url}")
    public ResponseEntity<String> encodeUrl(@PathVariable String url){
        return  ResponseEntity.ok(urlService.encodeAndSave(url));
    }

    @GetMapping("/{encodedUrl}")
    public ResponseEntity<String> getUrl(@PathVariable String encodedUrl){
        return ResponseEntity.ok(urlService.decodeUrl(encodedUrl));
    }

}
