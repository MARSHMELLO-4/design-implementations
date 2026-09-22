package com.design.UrlShortner.service;


import com.design.UrlShortner.Entity.TicketId;
import com.design.UrlShortner.Repository.TicketIdRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Random;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class TicketIdService {

    private final TicketIdRepository ticketIdRepository;

    private final Random random = new Random();

    private final TreeSet<Integer> availableRangeElements = IntStream.rangeClosed(0,3)
            .boxed()
            .collect(Collectors.toCollection(TreeSet::new));

    public TicketIdService(TicketIdRepository ticketIdRepository){
        this.ticketIdRepository = ticketIdRepository;
    }

    public long getTicketId(){
        //first we have to get the random number from the availableRangeElements
        int randomPosition = random.nextInt(availableRangeElements.size());

        long randomIndex = availableRangeElements
                .stream()
                .skip(randomPosition)
                .findFirst()
                .orElseThrow();

        System.out.println("Random Index generated : " + randomIndex);

        //now we have to get the field from it
        TicketId ticketId = ticketIdRepository.findById(randomIndex)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "Ticket ID not found"
                ));

        //now
        int ans = ticketId.getCurrent();

        //incremene the current by 1
        ticketId.setCurrent(ans + 1);
        //if the current number exceed end
        if(ticketId.getEnd() < ticketId.getCurrent()) {
            //then remove the id from the available range
            availableRangeElements.remove((int) randomIndex);
        }

        //save the ticket id back
        ticketIdRepository.save(ticketId);
        return ans;
    }

}
