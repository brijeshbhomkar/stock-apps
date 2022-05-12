package com.stocks.oi.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stocks.oi.document.Quote.Quote;
import com.stocks.oi.mongo.repository.MasterQuoteRepository;
import com.stocks.oi.util.Endpoints;
import com.stocks.oi.util.RequestHeader;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class MasterQuoteService {

    @Autowired
    private MasterQuoteRepository masterQuoteRepository;

    @Autowired
    private OpenInterestCallerImpl openInterestCaller;

    public List<String> findMasterQuote() {
        List<String> masterQuotes = null;
        try {
            final String data = openInterestCaller.caller(Endpoints.MASTER_QUOTE, null);
            if (data != null && !data.isEmpty()) {
                masterQuotes = new ObjectMapper().readValue(data, new TypeReference<List<String>>() {
                });
            }
        } catch (Exception e) {
            log.error("Failed to get the data! Retrying again " + e.getMessage());
        }
        return masterQuotes;
    }

    public void save(Quote quote) {
        if (quote != null) {
            masterQuoteRepository.save(quote);
        }
    }

    public List<Quote> findAll() {
        return masterQuoteRepository.findAll();
    }
}
