package com.stocks.oi.controller;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.stocks.oi.response.bullish.OptionOpenInterest;
import com.stocks.oi.util.DateUtil;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/oi")
public class OpenInterestIndexController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/index/{symbol}")
    public List<OptionOpenInterest> findOpenInterest(@PathVariable final String symbol) {
        Map<String, List<OptionOpenInterest>> resultMap = new HashMap<>();
        MongoCollection<Document> collection = mongoTemplate.getCollection("PRICE-UP-OI-UP");
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("_id", DateUtil.getDate(new Date()));
        FindIterable<Document> result = collection.find(whereQuery);
        Document document = result.first();
        List<Document> bullish = (List<Document>) document.get("bullish");
        List<String> symbols = bullish.stream().map(s -> (String)s.get("symbol")).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(symbols)) {

        }
        return null;
    }
}
