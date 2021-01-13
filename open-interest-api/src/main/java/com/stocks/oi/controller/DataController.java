package com.stocks.oi.controller;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.stocks.oi.document.Quote.Quote;
import com.stocks.oi.mongo.repository.MasterQuoteRepository;
import com.stocks.oi.response.bullish.OptionOpenInterest;
import com.stocks.oi.util.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequestMapping("/api/oi")
public class DataController {

    //TODO: This is done only for nifty and banknifty for time being

    private static final String LAST_WEEK_THRSDAY = DateUtil.getThrusdayOfWeek();

    private static final String CURRENT_WEEK_THRSDAY = DateUtil.getThrusdayOfWeek();

    private static final String LAST_THRSDAY_OF_MONTH = DateUtil.getLastThrusdayOfMonth();

    private static final List<String> INDICES = new ArrayList<>();

    static {
        INDICES.add("NIFTY");
        INDICES.add("BANKNIFTY");
    }

    @Autowired
    private MasterQuoteRepository masterQuoteRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/symbols/equities")
    public List<String> equitiesSymbol() {
        log.info("Find equities symbols");
        List<Quote> masterQuotes = masterQuoteRepository.findAll();
        return masterQuotes
                .stream()
                .filter(s -> s.getQuote() != null)
                .map(s -> s.getQuote())
                .collect(Collectors.toList());
    }

    @GetMapping("/symbols/indices")
    public List<String> indexSymbols() {
        return INDICES;
    }

    @GetMapping("/{symbol}")
    public Map<String, List<OptionOpenInterest>> openInterest(@PathVariable final String symbol) {
        Map<String, List<OptionOpenInterest>> result = new HashMap<>();
        MongoCollection<Document> collection = mongoTemplate.getCollection(symbol);
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("expiryDate", CURRENT_WEEK_THRSDAY);
        FindIterable<Document> findIterable = collection.find(whereQuery);
        Document document = findIterable.first();
        List<Document> chains = (List<Document>) document.get("chains");

        //find all contracts
        Set<String> contracts = new HashSet<>();
        chains.stream().forEach(s -> {
            contracts.add(s.getString("identifier"));
        });

        contracts.stream().forEach(s -> {
            List<OptionOpenInterest> opentionOpenInterests = chains
                    .stream()
                    .filter(y -> y.getString("identifier").equalsIgnoreCase(s))
                    .filter(y -> y.getDouble("openInterest") > 0)
                    .filter(y -> y.getDouble("totalTradedVolume") > 0).map(m -> {
                        OptionOpenInterest value = new OptionOpenInterest();
                        value.setTimestamp(m.getString("timestamp"));
                        value.setExpiryDate(m.getString("expiryDate"));
                        value.setStrike(m.getDouble("strikePrice"));
                        value.setOptionType(m.getString("type"));
                        value.setOpenInterest(m.getDouble("openInterest"));
                        value.setVolume(m.getDouble("totalTradedVolume"));
                        value.setChangeInOpenInterest(m.getDouble("changeinOpenInterest"));
                        value.setTotalBuyQuantity(m.getDouble("totalBuyQuantity"));
                        value.setTotalSellQuantity(m.getDouble("totalSellQuantity"));
                        value.setPchangeInOpenInterest(m.getDouble("pchangeinOpenInterest"));
                        value.setBidQty(m.getDouble("bidQty"));
                        value.setAskQty(m.getDouble("askQty"));
                        value.setBidPrice(m.getDouble("bidprice"));
                        value.setAskPrice(m.getDouble("askPrice"));
                        value.setUnderlyingValue(m.getDouble("underlyingValue"));
                        return value;
                    }).collect(Collectors.toList());
            result.put(s, opentionOpenInterests);
        });

        return result;
    }
}
