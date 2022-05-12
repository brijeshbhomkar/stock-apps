package com.stocks.oi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stocks.oi.persisters.DailyOpenInterestEquitiesPCRPersister;
import com.stocks.oi.persisters.DailyOpenInterestIndiciesPCRPersister;
import com.stocks.oi.response.oi.chain.DailyOpenInterestPCR;
import com.stocks.oi.response.oi.chain.OpenInterestChainResponse;
import com.stocks.oi.util.Endpoints;
import com.stocks.oi.util.RequestHeader;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class DailyOpenInterestPCRService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private OpenInterestCallerImpl openInterestCaller;

    @Autowired
    private DailyOpenInterestEquitiesPCRPersister dailyOpenInterestEquitiesPCRPersister;

    @Autowired
    private DailyOpenInterestIndiciesPCRPersister dailyOpenInterestIndiciesPCRPersister;

    public DailyOpenInterestPCR getOpenInterestEquitiesPcr(final String symbol) {
        log.info(" started calculating open interest pcr for symbol " + symbol);
        final OpenInterestChainResponse openInterestChainResponse = remoteOpenInterestEquitiesCall(symbol);
        if (openInterestChainResponse != null) {
            return dailyOpenInterestEquitiesPCRPersister.persist(openInterestChainResponse, symbol);
        }
        return null;
    }

    public OpenInterestChainResponse remoteOpenInterestEquitiesCall(String symbol) {
        OpenInterestChainResponse openInterestResponse = null;
        try {
            final String data = openInterestCaller.caller(Endpoints.EQUITIES_OPEN_INTEREST + symbol, RequestHeader.COOKIE_EQP);
            if (data != null && !data.isEmpty()) {
                openInterestResponse = new ObjectMapper().readValue(data, OpenInterestChainResponse.class);
            }
        } catch (Exception e) {
            log.error("Failed to get the data! Retrying again " + e.getMessage());
        }
        return openInterestResponse;
    }

    public DailyOpenInterestPCR getOpenInterestIndiciesPcr(final String symbol) {
        log.info(" started calculating open interest pcr for symbol " + symbol);
        final OpenInterestChainResponse openInterestChainResponse = remoteOpenInterestIndiciesCall(symbol);
        if (openInterestChainResponse != null) {
            return dailyOpenInterestIndiciesPCRPersister.persist(openInterestChainResponse, symbol);
        }
        return null;
    }

    public OpenInterestChainResponse remoteOpenInterestIndiciesCall(String symbol) {
        OpenInterestChainResponse openInterestResponse = null;
        try {
            final String data = openInterestCaller.caller(Endpoints.INDICES_OPEN_INTEREST + symbol, RequestHeader.COOKIE_EQP);
            if (data != null && !data.isEmpty()) {
                openInterestResponse = new ObjectMapper().readValue(data, OpenInterestChainResponse.class);
            }
        } catch (Exception e) {
            log.error("Failed to get the data! Retrying again " + e.getMessage());
        }
        return openInterestResponse;
    }
}