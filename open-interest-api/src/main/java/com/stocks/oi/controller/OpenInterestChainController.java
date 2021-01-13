package com.stocks.oi.controller;

import com.stocks.oi.document.Quote.Quote;
import com.stocks.oi.mongo.repository.EquitiesOpenInterestRepository;
import com.stocks.oi.mongo.repository.MasterQuoteRepository;
import com.stocks.oi.response.oi.chain.OpenInterestChainResponse;
import com.stocks.oi.service.EquitiesOpenInterestService;
import com.stocks.oi.service.IndicesOpenInterestService;
import com.stocks.oi.util.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/oi")
public class OpenInterestChainController {

    @Autowired
    private EquitiesOpenInterestService equitiesOpenInterestService;

    @Autowired
    private IndicesOpenInterestService indicesOpenInterestService;

    @Autowired
    private EquitiesOpenInterestRepository equitiesOpenInterestRepository;

    @Autowired
    private MasterQuoteRepository masterQuoteRepository;

    @GetMapping("/equities/quotes")
    public String loadMasterQuotes() {
        log.info("Loading master quotes for equities ");
        List<String> quotes = equitiesOpenInterestService.findMasterQuote();
        quotes.stream().forEach(s -> {
            Quote quote = new Quote();
            quote.setQuote(s);
            masterQuoteRepository.save(quote);
        });
        return HttpStatus.OK.toString();
    }

    @GetMapping("/equities/daily")
    public String loadEquitiesOpenInterest() {
        log.info("Loading all open interest daily for equities ");
        List<Quote> quotes = masterQuoteRepository.findAll();
        quotes.stream().forEach(s -> {
            log.info("Loading open interest for " + s);
            callEquities(s.getQuote());
        });
        return HttpStatus.OK.toString();
    }

    @GetMapping("/indices/daily")
    public String loadIndicesOpenInterest() {
        log.info("Loading all open interest daily for indicies ");
        List<String> quotes = Arrays.asList("NIFTY,BANKNIFTY,NIFTYIT");
        quotes.stream().forEach(s -> {
            log.info("Loading open interest for " + s);
            callIndices(s);
        });
        return HttpStatus.OK.toString();
    }

    @GetMapping("/equities/{symbol}")
    public OpenInterestChainResponse processEquitiesOpenInterest(@PathVariable final String symbol) {
        log.info(" processing open interest for day " + LocalDate.now().toString());
        return callEquities(symbol);
    }

    @GetMapping("/indices/{symbol}")
    public OpenInterestChainResponse processIndicesOpenInterest(@PathVariable final String symbol) {
        log.info(" processing open interest for day " + LocalDate.now().toString());
        return callIndices(symbol);
    }

    private OpenInterestChainResponse callEquities(String symbol) {
        return equitiesOpenInterestService.getEquitiesOpenInterest(symbol);
    }

    private OpenInterestChainResponse callIndices(String symbol) {
        return indicesOpenInterestService.getIndicesOpenInterest(symbol);
    }

    @GetMapping("/equities/query/{symbol}")
    public Document findEquitiesOpenInterest(@PathVariable final String symbol) {
        final String lastThrusdayOfMonth = DateUtil.getLastThrusdayOfMonth();
        return equitiesOpenInterestService.findEquitiesOpenInterest(symbol, lastThrusdayOfMonth);
    }

    @GetMapping("/indices/query/{symbol}")
    public Document findIndicesOpenInterest(@PathVariable final String symbol) {
        final String thrusdayOfWeek = DateUtil.getThrusdayOfWeek();
        return indicesOpenInterestService.findIndicesOpenInterest(symbol, thrusdayOfWeek);
    }
}
