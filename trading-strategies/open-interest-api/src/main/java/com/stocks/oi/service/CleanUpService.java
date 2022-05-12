package com.stocks.oi.service;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.stocks.oi.document.Quote.Quote;
import com.stocks.oi.mongo.repository.EquitiesOpenInterestRepository;
import com.stocks.oi.mongo.repository.MasterQuoteRepository;
import com.stocks.oi.util.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class CleanUpService {

    @Autowired
    private EquitiesOpenInterestRepository equitiesOpenInterestRepository;

    @Autowired
    private MasterQuoteRepository masterQuoteRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Scheduled(cron = "0 45 15 * * 1-5")
    public void cleanupEquities() {
        log.info("Started clean up service " + LocalDateTime.now());
        List<Quote> masterQuotes = masterQuoteRepository.findAll();
        masterQuotes.forEach(s -> {
            log.info("Checking clean up for symbol " + s.getQuote());
            MongoCollection<Document> collection = mongoTemplate.getCollection(s.getQuote());
            BasicDBObject whereQuery = new BasicDBObject();
            whereQuery.put("expiryDate", DateUtil.formateDate());
            FindIterable<Document> result = collection.find(whereQuery);
            Document doc = result.first();
            if (doc != null) {
                log.info("Removing document with expiry date " + doc.get("expiryDate"));
                mongoTemplate.remove(doc);
            }
        });
    }

    @Scheduled(cron = "0 0 16 * * 1-5")
    public void cleanupIndices() {
        log.info("Started clean up service " + LocalDateTime.now());
        List<String> quotes = new ArrayList<>();
        quotes.add("NIFTY");
        quotes.add("BANKNIFTY");
        quotes.forEach(s -> {
            log.info("Checking clean up for symbol " + s);
            MongoCollection<Document> collection = mongoTemplate.getCollection(s);
            BasicDBObject whereQuery = new BasicDBObject();
            whereQuery.put("expiryDate", DateUtil.formateDate());
            FindIterable<Document> result = collection.find(whereQuery);
            Document doc = result.first();
            if (doc != null) {
                log.info("Removing document with expiry date " + doc.get("expiryDate"));
                mongoTemplate.remove(doc);
            }
        });
    }
}
