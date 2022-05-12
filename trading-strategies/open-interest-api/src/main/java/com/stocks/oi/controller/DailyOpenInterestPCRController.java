package com.stocks.oi.controller;


import com.stocks.oi.response.oi.chain.DailyOpenInterestPCR;
import com.stocks.oi.service.DailyOpenInterestPCRService;
import com.stocks.oi.service.MasterQuoteService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequestMapping("/api/oi")
public class DailyOpenInterestPCRController {

    @Autowired
    private DailyOpenInterestPCRService dailyOpenInterestPCRService;

    @Autowired
    private MasterQuoteService masterQuoteService;

    @GetMapping("/pcr/equities/daily")
    public List<DailyOpenInterestPCR> equitiesPcr() {
        log.info("Getting daily pcr for equities " + LocalDate.now());
        List<String> quotes = masterQuoteService.findMasterQuote();
        log.info(" quotes size " + quotes != null && quotes.size() > 0 ? quotes.size() : null);
        List<DailyOpenInterestPCR> pcrs = new ArrayList<>();
        quotes.stream().forEach(s -> pcrs.add(dailyOpenInterestPCRService.getOpenInterestEquitiesPcr(s)));
        List<DailyOpenInterestPCR> highestpcrs = pcrs
                .stream()
                .filter(s -> s != null && s.getPcrOI() > 0)
                .sorted(Comparator.comparing(DailyOpenInterestPCR::getPcrOI).reversed())
                .collect(Collectors.toList());
        return highestpcrs;
    }


    @PostMapping("/pcr/equity/{symbol}")
    public DailyOpenInterestPCR equitySymbolPcr(@PathVariable final String symbol) {
        log.info(" Getting pcr ration for equity " + symbol);
        return dailyOpenInterestPCRService.getOpenInterestEquitiesPcr(symbol);
    }
}
