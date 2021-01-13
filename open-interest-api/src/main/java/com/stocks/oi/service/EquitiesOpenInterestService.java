package com.stocks.oi.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.ReplaceOptions;
import com.stocks.oi.response.oi.chain.OpenInterestChain;
import com.stocks.oi.response.oi.chain.OpenInterestChainJson;
import com.stocks.oi.response.oi.chain.OpenInterestChainResponse;
import com.stocks.oi.util.Endpoints;
import com.stocks.oi.util.RequestHeader;
import lombok.extern.log4j.Log4j2;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.net.http.HttpClient;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.eq;

@Log4j2
@Service
public class EquitiesOpenInterestService {

    private HttpClient client = HttpClient.newHttpClient();

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private OpenInterestCallerImpl openInterestCaller;

    public OpenInterestChainResponse getEquitiesOpenInterest(String symbol) {
        return persist(makeCall(symbol), symbol);
    }

    private OpenInterestChainResponse persist(OpenInterestChainResponse openInterestChainResponse, final String symbol) {
        //1. check if collection exits, i.e. SBIN
        if (openInterestChainResponse != null) {
            MongoCollection collection = null;
            if (!mongoTemplate.getCollectionNames().contains(symbol)) {
                collection = mongoTemplate.createCollection(symbol);
            } else {
                collection = mongoTemplate.getCollection(symbol);
            }

            // 2. find all the expiry dates which has option chain data
            List<String> expiryDates = openInterestChainResponse.getRecords().getExpiryDates();
            if (!CollectionUtils.isEmpty(expiryDates)) {
                MongoCollection finalCollection = collection;
                expiryDates.stream().forEach(expiryDate -> {
                    //find all matching option chain with expiry dates
                    List<OpenInterestChain> chains = openInterestChainResponse.getFiltered().getData()
                            .stream().filter(k -> k.getExpiryDate().equalsIgnoreCase(expiryDate)).collect(Collectors.toList());

                    org.bson.Document wrapper = findExistingDocument(expiryDate, finalCollection);

                    //if expiry dates has option chain data create a document wrapper to insert into main collection
                    if (!CollectionUtils.isEmpty(chains)) {
                        //search for existing expiry date
                        if (wrapper != null) {
                            List<Document> documents = (List<Document>) wrapper.get("chains");
                            documents.addAll(findDocuments(chains));
                            final ObjectId id = wrapper.getObjectId("_id");
                            wrapper.put("chains", documents);
                            finalCollection.replaceOne(eq("_id", id), wrapper, new ReplaceOptions().upsert(true));
                        } else {
                            //create new wrapper
                            wrapper = new Document();
                            wrapper.put("expiryDate", expiryDate);
                            wrapper.put("chains", findDocuments(chains));
                            finalCollection.insertOne(wrapper);
                        }
                    }
                });
            }
            return openInterestChainResponse;
        }
        return null;
    }

    private List<Document> findDocuments(List<OpenInterestChain> chains) {
        List<org.bson.Document> documents = new ArrayList<>();
        chains.stream().forEach(chain -> {
            OpenInterestChainJson ce = null;
            OpenInterestChainJson pe = null;
            if (chain.getCe() != null) {
                documents.add(createDocument(chain.getCe()));
            }
            if (chain.getPe() != null) {
                documents.add(createDocument(chain.getPe()));
            }
        });
        return documents;
    }

    private Document createDocument(OpenInterestChainJson json) {
        org.bson.Document document = new org.bson.Document();
        document.put("timestamp", LocalDateTime.now().toString());
        document.put("strikePrice", json.getStrikePrice());
        document.put("expiryDate", json.getExpiryDate());
        document.put("underlying", json.getUnderlying());
        document.put("identifier", json.getIdentifier());
        document.put("openInterest", json.getOpenInterest());
        document.put("changeinOpenInterest", json.getChangeinOpenInterest());
        document.put("pchangeinOpenInterest", json.getPchangeinOpenInterest());
        document.put("totalTradedVolume", json.getTotalTradedVolume());
        document.put("impliedVolatility", json.getImpliedVolatility());
        document.put("lastPrice", json.getLastPrice());
        document.put("change", json.getChange());
        document.put("pChange", json.getpChange());
        document.put("totalBuyQuantity", json.getTotalBuyQuantity());
        document.put("totalSellQuantity", json.getTotalSellQuantity());
        document.put("bidQty", json.getBidQty());
        document.put("bidprice", json.getBidprice());
        document.put("askQty", json.getAskQty());
        document.put("askPrice", json.getAskPrice());
        document.put("underlyingValue", json.getUnderlyingValue());
        document.put("type", json.getType());
        return document;
    }

    private Document findExistingDocument(String expiryDate, MongoCollection collection) {
        BasicDBObject query = new BasicDBObject();
        query.append("expiryDate", expiryDate);
        FindIterable<Document> findIterable = collection.find(query);
        return findIterable.first();
    }

    public OpenInterestChainResponse makeCall(String symbol) {
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

    public List<String> findMasterQuote() {
        List<String> masterQuotes = null;
        try {
            final String data = openInterestCaller.caller(Endpoints.MASTER_QUOTE, RequestHeader.COOKIE_QUOTES);
            if (data != null && !data.isEmpty()) {
                masterQuotes = new ObjectMapper().readValue(data, new TypeReference<List<String>>() {
                });
            }
        } catch (Exception e) {
            log.error("Failed to get the data! Retrying again " + e.getMessage());
        }
        return masterQuotes;
    }

    public Document findEquitiesOpenInterest(String symbol, String lastThrusdayOfMonth) {
        MongoCollection collection = mongoTemplate.getCollection(symbol);
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("expiryDate", lastThrusdayOfMonth);
        FindIterable<Document> result = collection.find(whereQuery);
        return result.first();
    }
}
