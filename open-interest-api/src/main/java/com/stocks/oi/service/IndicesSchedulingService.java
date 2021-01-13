package com.stocks.oi.service;

import com.stocks.oi.mongo.repository.MasterQuoteRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class IndicesSchedulingService {

    @Autowired
    private CleanUpService cleanUpService;

    @Autowired
    private IndicesOpenInterestService indicesOpenInterestService;

    @Autowired
    private MasterQuoteRepository masterQuoteRepository;

    @Scheduled(cron = "0 15/2 9-15 * * 1-5")
    public void indicesOpenInterestLoader() {
        log.info("Started scheduler for open interest for indices " + LocalDateTime.now());
        List<String> quotes = new ArrayList<>();
        quotes.add("NIFTY");
        quotes.add("BANKNIFTY");
        quotes.stream().forEach(s -> {
            log.info("Loading open interest for " + s);
            indicesOpenInterestService.getIndicesOpenInterest(s);
        });
    }
}
