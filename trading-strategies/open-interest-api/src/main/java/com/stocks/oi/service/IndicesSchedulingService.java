package com.stocks.oi.service;

import com.stocks.oi.mongo.repository.MasterQuoteRepository;
import com.stocks.oi.response.oi.chain.DailyOpenInterestPCR;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class IndicesSchedulingService {

    @Autowired
    private CleanUpService cleanUpService;

    @Autowired
    private IndicesOpenInterestService indicesOpenInterestService;

    @Autowired
    private MasterQuoteRepository masterQuoteRepository;

    @Autowired
    private DailyOpenInterestPCRService dailyOpenInterestPCRService;

//    @Scheduled(cron = "0 15/5 9-15 * * 1-5")
//    public void indicesOpenInterestLoader() {
//        log.info("Started scheduler for open interest for indices " + LocalDateTime.now());
//        List<String> quotes = new ArrayList<>();
//        quotes.add("NIFTY");
//        quotes.add("BANKNIFTY");
//        quotes.stream().forEach(s -> {
//            log.info("Loading open interest for " + s);
//            indicesOpenInterestService.getIndicesOpenInterest(s);
//        });
//    }

    @Scheduled(cron = "0 15/5 9-15 * * 1-5")
    public void indicesOpenInterestLoader() {
        log.info("Started scheduler for open interest for indices " + LocalDateTime.now());
        List<String> quotes = new ArrayList<>();
        quotes.add("NIFTY");
        quotes.add("BANKNIFTY");

        //List<DailyOpenInterestPCR> pcrs = new ArrayList<>();
        quotes.stream().forEach(s -> {
            log.info("Loading open interest for " + s);
            dailyOpenInterestPCRService.getOpenInterestIndiciesPcr(s);
            //pcrs.add(dailyOpenInterestPCRService.getOpenInterestIndiciesPcr(s));
        });

//        List<DailyOpenInterestPCR> highestpcrs = pcrs
//                .stream()
//                .filter(s -> s != null && s.getPcrOI() > 0)
//                .sorted(Comparator.comparing(DailyOpenInterestPCR::getPcrOI).reversed())
//                .collect(Collectors.toList());
//
//        highestpcrs.stream().forEach(s -> log.info(s.toString()));
    }
}
