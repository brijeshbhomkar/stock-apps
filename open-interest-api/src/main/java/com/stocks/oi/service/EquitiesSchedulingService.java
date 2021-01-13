package com.stocks.oi.service;

import com.stocks.oi.document.Quote.Quote;
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
public class EquitiesSchedulingService {

    @Autowired
    private CleanUpService cleanUpService;

    @Autowired
    private EquitiesOpenInterestService equitiesOpenInterestService;

    @Autowired
    private MasterQuoteRepository masterQuoteRepository;

    private static List<String> exclusion = new ArrayList<String>();

    static {
        exclusion.add("INFRATEL");//TODO: no data
        //exclusion.add("ITC");//TODO: data available
        exclusion.add("M&MFIN");//TODO: no data
        exclusion.add("M&M");//TODO: no data
        //exclusion.add("LUPIN"); //TODO: data available
        //exclusion.add("LALPATHLAB"); //TODO: data available
        exclusion.add("L&TFH"); //TODO: no data
        //exclusion.add("KOTAKBANK"); //TODO: data available
        //exclusion.add("JUBLFOOD"); //TODO: data available
        //exclusion.add("JSWSTEEL"); //TODO: data available
        //exclusion.add("JINDALSTEL"); //TODO: data available
        exclusion.add("IOC");  //TODO: data available
        //exclusion.add("INDUSTOWER"); //TODO: data available
        //exclusion.add("INDUSINDBK"); //TODO: data available
        //exclusion.add("INDIGO"); //TODO: data available
    }

//    @Scheduled(cron = "0 15/5 9-15 * * 1-5")
//    public void equitiesOpenInterestLoader() {
//        log.info("started scheduler for open interest for equities " + LocalDateTime.now());
//        List<Quote> equities = masterQuoteRepository.findAll();
//        equities.stream().forEach(s -> {
//            log.info("Loading open interest for " + s.getQuote());
//            equitiesOpenInterestService.getEquitiesOpenInterest(s.getQuote());
//        });
//    }
}
