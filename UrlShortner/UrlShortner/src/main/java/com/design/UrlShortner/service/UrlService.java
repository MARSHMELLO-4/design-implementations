package com.design.UrlShortner.service;


import com.design.UrlShortner.Entity.URL;
import com.design.UrlShortner.Repository.UrlRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UrlService {

    private UrlRepository urlRepository;
    private TicketIdService ticketIdService;
    private static final String CHARACTERS =
            "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public UrlService(UrlRepository urlRepository, TicketIdService ticketIdService){
        this.urlRepository = urlRepository;
        this.ticketIdService = ticketIdService;
    }

    //now we have to first save the url

    public String encodeAndSave(String url){
        //here now we have to write the logic
        URL url1 = new URL();
        long id = ticketIdService.getTicketId();
        System.out.println("Id got from the ticketId service : " + id);
        url1.setId(id);

        //now wehave to encode this id from the character map which is also random
        String encodedUrl = this.encode(id);
        url1.setEncodedUrl(encodedUrl);
        url1.setOriginalUrl(url);

        urlRepository.save(url1);

        return encodedUrl;
    }

    public String encode(long id) {

        if (id == 0) {
            return String.valueOf(CHARACTERS.charAt(0));
        }

        StringBuilder encoded = new StringBuilder();

        while (id > 0) {
            int remainder = (int) (id % 62);

            encoded.append(CHARACTERS.charAt(remainder));

            id = id / 62;
        }

        return encoded.reverse().toString();
    }

    public String decodeUrl(String encodedUrl) {

        URL url = urlRepository.findByEncodedUrl(encodedUrl)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "URL not found"
                ));

        return url.getOriginalUrl();
    }


}
