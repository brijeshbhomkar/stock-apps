package com.stocks.oi.controller;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.stocks.oi.document.Quote.Quote;
import com.stocks.oi.mongo.repository.MasterQuoteRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/oi")
public class CleanUpController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MasterQuoteRepository masterQuoteRepository;

    @GetMapping("/equities")
    public String cleanUpEquities() {
        List<Quote> quotes = masterQuoteRepository.findAll();
        quotes.forEach(s -> {
            BasicDBObject query = new BasicDBObject();
            query.put("symbol",s.getQuote());
            mongoTemplate.remove(query, s.getQuote());
        });
        return HttpStatus.OK.toString();
    }

    @GetMapping("/indices")
    public String cleanUpIndices() {
        List<String> indices = new ArrayList<>();
        indices.add("NIFTY");
        indices.add("BANKNIFTY");
        indices.forEach(s -> {
            mongoTemplate.remove(s);
        });
        return HttpStatus.OK.toString();
    }
}
