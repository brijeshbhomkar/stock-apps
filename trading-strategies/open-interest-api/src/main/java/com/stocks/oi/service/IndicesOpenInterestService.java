package com.stocks.oi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stocks.oi.mongo.repository.IndicesOpenInterestRepository;
import com.stocks.oi.persisters.DailyOpenInterestIndiciesPCRPersister;
import com.stocks.oi.persisters.OpenInterestChainPersister;
import com.stocks.oi.response.oi.chain.DailyOpenInterestPCR;
import com.stocks.oi.response.oi.chain.OpenInterestChainResponse;
import com.stocks.oi.util.Endpoints;
import com.stocks.oi.util.RequestHeader;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpClient;

@Log4j2
@Service
public class IndicesOpenInterestService {

    @Autowired
    private OpenInterestCallerImpl openInterestCaller;

    @Autowired
    private IndicesOpenInterestRepository indicesOpenInterestRepository;

    @Autowired
    private DailyOpenInterestIndiciesPCRPersister dailyOpenInterestIndiciesPCRPersister;

    @SneakyThrows
    public DailyOpenInterestPCR getIndicesOpenInterest(String symbol) {
        OpenInterestChainResponse openInterestChainResponse = remoteOpenInterestCall(symbol);
        if (openInterestChainResponse != null) {
            return dailyOpenInterestIndiciesPCRPersister.persist(openInterestChainResponse, symbol);
        }
        return null;
    }

    private OpenInterestChainResponse remoteOpenInterestCall(String symbol) {
        OpenInterestChainResponse openInterestResponse = null;
        try {
            final String data = openInterestCaller.caller(Endpoints.INDICES_OPEN_INTEREST + symbol, RequestHeader.COOKIE);
            if (data != null && !data.isEmpty()) {
                openInterestResponse = new ObjectMapper().readValue(data, OpenInterestChainResponse.class);
            }
        } catch (Exception e) {
            log.error("Failed to get the data! Retrying again " + e.getMessage());
        }
        return openInterestResponse;
    }

//    public Document findIndicesOpenInterest(String symbol, String expiryDate) {
//        MongoCollection collection = mongoTemplate.getCollection(symbol);
//        BasicDBObject whereQuery = new BasicDBObject();
//        whereQuery.put("expiryDate", expiryDate);
//        FindIterable<Document> result = collection.find(whereQuery);
//        return result.first();
//    }
}
