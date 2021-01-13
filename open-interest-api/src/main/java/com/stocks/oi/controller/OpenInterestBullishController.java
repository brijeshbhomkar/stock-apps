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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/oi")
public class OpenInterestBullishController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/bullish")
    public Map<String, List<OptionOpenInterest>> findBullish() {
        Map<String, List<OptionOpenInterest>> resultMap = new HashMap<>();
        MongoCollection<Document> collection = mongoTemplate.getCollection("PRICE-UP-OI-UP");
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("_id", DateUtil.getDate(new Date()));
        FindIterable<Document> result = collection.find(whereQuery);
        Document document = result.first();
        List<Document> bullish = (List<Document>) document.get("bullish");
        List<String> symbols = bullish.stream().map(s -> (String)s.get("symbol")).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(symbols)) {
            symbols.stream().forEach(s -> {
                List<OptionOpenInterest> data = new ArrayList<>();
                bullish.stream().filter(k-> k.getString("symbol").equalsIgnoreCase(s)).forEach( y -> {
                    OptionOpenInterest value = new OptionOpenInterest();
                    value.setExpiryDate(DateUtil.formatExpiryDate(y.getDate("expiry")));
                    value.setStrike(y.getDouble("strike"));
                   // value.setSymbol(y.getString("symbol"));
                    value.setOptionType(y.getString("optionType"));
                    value.setVolume(y.getDouble("volume"));
                    value.setOiChange(y.getDouble("oiChange"));
                    value.setOpenInterest(y.getDouble("latestOI"));
                    value.setCurrentPrice(y.getDouble("underlyValue"));
                    data.add(value);
                });
               resultMap.put(s, data);
           });
        }
        return resultMap;
    }


}
