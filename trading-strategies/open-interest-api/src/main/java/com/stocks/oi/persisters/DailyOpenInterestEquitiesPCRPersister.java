package com.stocks.oi.persisters;

import com.mongodb.client.MongoCollection;
import com.stocks.oi.response.oi.chain.*;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DailyOpenInterestEquitiesPCRPersister {

    @Autowired
    private MongoTemplate mongoTemplate;

    private static final String MONGO_TABLE_NAME = "DAILY_OPEN_INTEREST_EQUITIES_PCR";

    public DailyOpenInterestPCR persist(final OpenInterestChainResponse openInterestChainResponse, String symbol) {
        DailyOpenInterestPCR dailyOpenInterestPCR = new DailyOpenInterestPCR();
        if (openInterestChainResponse != null) {
            MongoCollection collection = null;
//            if (!mongoTemplate.getCollectionNames().contains(MONGO_TABLE_NAME)) {
//                collection = mongoTemplate.createCollection(MONGO_TABLE_NAME);
//            } else {
//                collection = mongoTemplate.getCollection(MONGO_TABLE_NAME);
//            }

            dailyOpenInterestPCR.setSymbol(symbol);
            dailyOpenInterestPCR.setCurrentDateTime(LocalDateTime.now());

            OpenInterestChainFiltered filtered = openInterestChainResponse.getFiltered();
            if (filtered != null) {
                CE ce = filtered.getCe();
                CE pe = filtered.getPe();

                double totOiPcr = (Double.parseDouble(pe.getTotOI()) / Double.parseDouble(ce.getTotOI()));
                dailyOpenInterestPCR.setPcrOI(totOiPcr);

                //double totVolPcr = (Double.parseDouble(pe.getTotVol()) / Double.parseDouble(ce.getTotVol()));
                //dailyOpenInterestPCR.setPcrVolume(totVolPcr);

                DecimalFormat df = new DecimalFormat("#.##");
                df.setRoundingMode(RoundingMode.FLOOR);

                totOiPcr = new Double(df.format(totOiPcr));
                //totVolPcr = new Double(df.format(totVolPcr));

                dailyOpenInterestPCR.setMarket(totOiPcr <= 0.75 ? "Bullish" : totOiPcr >= 1.00 ? "Bearish" : "Neutral");

                // CE
                List<OpenInterestChainJson> ceList = filtered.getData().stream()
                        .filter(s -> s.getCe() != null)
                        .map(m -> m.getCe())
                        .sorted(Comparator.comparingDouble(OpenInterestChainJson::getOpenInterest).reversed())
                        .collect(Collectors.toList());

                // PE
                List<OpenInterestChainJson> peList = filtered.getData().stream()
                        .filter(f -> f.getPe() != null)
                        .map(m -> m.getPe())
                        .sorted(Comparator.comparingDouble(OpenInterestChainJson::getOpenInterest).reversed())
                        .collect(Collectors.toList());

                OpenInterestChainJson highestCEOI = ceList != null && ceList.size() > 0 ? ceList.get(0) : null;
                OpenInterestChainJson highestPEOI = peList != null && peList.size() > 0 ? peList.get(0) : null;

                if (highestCEOI != null) {
                    dailyOpenInterestPCR.setCallLtp(highestCEOI.getUnderlyingValue());
                    dailyOpenInterestPCR.setCallStrikePrice(highestCEOI.getStrikePrice());
                  //  dailyOpenInterestPCR.setMaxCallChangeOI(highestCEOI.getChange());
                  //  dailyOpenInterestPCR.setMaxCallOI(highestCEOI.getOpenInterest());
                  //  dailyOpenInterestPCR.setMaxCallVolume(highestCEOI.getTotalTradedVolume());
                    dailyOpenInterestPCR.setFirstResistance(highestCEOI.getStrikePrice());
                }

                if (highestPEOI != null) {
                    dailyOpenInterestPCR.setPutLtp(highestPEOI.getUnderlyingValue());
                    dailyOpenInterestPCR.setPutStrikePrice(highestPEOI.getStrikePrice());
                    //dailyOpenInterestPCR.setMaxPutChangeOI(highestPEOI.getChange());
                    //dailyOpenInterestPCR.setMaxPutOI(highestPEOI.getOpenInterest());
                    //dailyOpenInterestPCR.setMaxPutVolume(highestPEOI.getTotalTradedVolume());
                    dailyOpenInterestPCR.setFirstSupport(highestPEOI.getStrikePrice());
                }
            }

          //  Document document = createDocument(dailyOpenInterestPCR, symbol);
          //  collection.insertOne(document);
        }

        return dailyOpenInterestPCR;
    }

    private Document createDocument(DailyOpenInterestPCR pcr, String symbol) {
        org.bson.Document document = new org.bson.Document();
        document.put("symbol", symbol);
        document.put("currentDateTime", LocalDateTime.now().toString());
//        document.put("expiryDate", pcr.getExpiryDate());
//        document.put("maxCallOI", pcr.getMaxCallOI());
//        document.put("maxCallChangeOI", pcr.getMaxCallChangeOI());
//        document.put("maxCallVolume", pcr.getMaxCallVolume());
        document.put("callLtp", pcr.getCallLtp());
        document.put("callStrikePrice", pcr.getCallStrikePrice());
        document.put("resistance", pcr.getFirstResistance());
//        document.put("maxPutOI", pcr.getMaxPutOI());
//        document.put("maxPutChangeOI", pcr.getMaxPutChangeOI());
//        document.put("maxPutVolume", pcr.getMaxPutVolume());
        document.put("putLtp", pcr.getPutLtp());
        document.put("putStrikePrice", pcr.getPutStrikePrice());
        document.put("support", pcr.getFirstSupport());
        document.put("pcrOI", pcr.getPcrOI());
        //document.put("pcrVolume", pcr.getPcrVolume());
        document.put("market", pcr.getMarket());
        return document;
    }
}
