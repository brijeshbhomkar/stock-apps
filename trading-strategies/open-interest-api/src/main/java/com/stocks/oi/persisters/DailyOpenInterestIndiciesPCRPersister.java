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
public class DailyOpenInterestIndiciesPCRPersister {

    @Autowired
    private MongoTemplate mongoTemplate;

    private static final String MONGO_TABLE_NAME_NIFTY = "DAILY_OPEN_INTEREST_INDICIES_NIFTY";

    private static final String MONGO_TABLE_NAME_BANKNIFTY = "DAILY_OPEN_INTEREST_INDICIES_BANKNIFTY";

    public DailyOpenInterestPCR persist(final OpenInterestChainResponse openInterestChainResponse, String symbol) {
        DailyOpenInterestPCR dailyOpenInterestPCR = new DailyOpenInterestPCR();
        if (openInterestChainResponse != null) {
            MongoCollection collection = null;
//            if (!mongoTemplate.getCollectionNames().contains(MONGO_TABLE_NAME)) {
//                collection = mongoTemplate.createCollection(MONGO_TABLE_NAME);
//            } else {
//                collection = mongoTemplate.getCollection(MONGO_TABLE_NAME);
//            }
//            if (symbol.equalsIgnoreCase("BANKNIFTY")) {
//                if (!mongoTemplate.getCollectionNames().contains(MONGO_TABLE_NAME_BANKNIFTY)) {
//                    collection = mongoTemplate.createCollection(MONGO_TABLE_NAME_BANKNIFTY);
//                } else {
//                    collection = mongoTemplate.getCollection(MONGO_TABLE_NAME_BANKNIFTY);
//                }
//            } else {
//                if (!mongoTemplate.getCollectionNames().contains(MONGO_TABLE_NAME_NIFTY)) {
//                    collection = mongoTemplate.createCollection(MONGO_TABLE_NAME_NIFTY);
//                } else {
//                    collection = mongoTemplate.getCollection(MONGO_TABLE_NAME_NIFTY);
//                }
//            }

            dailyOpenInterestPCR.setSymbol(symbol);
            dailyOpenInterestPCR.setCurrentDateTime(LocalDateTime.now());

            OpenInterestChainFiltered filtered = openInterestChainResponse.getFiltered();

            if (filtered != null) {

                //get the range with in 1000 points up and down

                Double stockprice = Double.parseDouble(openInterestChainResponse.getRecords().getUnderlyingValue());
                int uppercircuit = (int) (stockprice + 1000);
                int lowercircuit = (int) (stockprice - 1000);

                List<OpenInterestChain> boundedOptionChains = filtered.getData().stream().filter(s -> s.getPe().getStrikePrice() <= uppercircuit && s.getCe().getStrikePrice() >= lowercircuit).collect(Collectors.toList());

                List<OpenInterestChainJson> peOptionChain = boundedOptionChains.stream().map(OpenInterestChain::getPe).collect(Collectors.toList());
                List<OpenInterestChainJson> ceOptionChain = boundedOptionChains.stream().map(OpenInterestChain::getCe).collect(Collectors.toList());

                double peTotOI = peOptionChain.stream().mapToDouble(OpenInterestChainJson::getOpenInterest).sum();
                double ceTotOI = ceOptionChain.stream().mapToDouble(OpenInterestChainJson::getOpenInterest).sum();

                double peTotVol = peOptionChain.stream().mapToDouble(OpenInterestChainJson::getTotalTradedVolume).sum();
                double ceTotVol = peOptionChain.stream().mapToDouble(OpenInterestChainJson::getTotalTradedVolume).sum();

               // CE ce = filtered.getCe();
                //CE pe = filtered.getPe();

                double totOiPcr = peTotOI / ceTotOI;
                //dailyOpenInterestPCR.setPcrOI(totOiPcr);

                double totVolPcr = peTotVol / ceTotVol;


                DecimalFormat df = new DecimalFormat("#.##");
                df.setRoundingMode(RoundingMode.FLOOR);

                totOiPcr = new Double(df.format(totOiPcr));

                dailyOpenInterestPCR.setPcrOI(totOiPcr);
                totVolPcr = new Double(df.format(totVolPcr));

                dailyOpenInterestPCR.setPcrVolume(totVolPcr);


                // CE
                List<OpenInterestChainJson> ceList = filtered.getData().stream()
                        .filter(s -> s.getCe() != null)
                        .map(m -> m.getCe())
                        .sorted(Comparator.comparingDouble(OpenInterestChainJson::getOpenInterest).reversed()).limit(2)
                        .collect(Collectors.toList());

                // PE
                List<OpenInterestChainJson> peList = filtered.getData().stream()
                        .filter(f -> f.getPe() != null)
                        .map(m -> m.getPe())
                        .sorted(Comparator.comparingDouble(OpenInterestChainJson::getOpenInterest).reversed()).limit(2)
                        .collect(Collectors.toList());

                OpenInterestChainJson highestCEOI = ceList != null && ceList.size() > 0 ? ceList.get(0) : null;
                OpenInterestChainJson highestPEOI = peList != null && peList.size() > 0 ? peList.get(0) : null;

                OpenInterestChainJson secondHighestCEOI = ceList != null && ceList.size() > 0 ? ceList.get(1) : null;
                OpenInterestChainJson secondHighestPEOI = peList != null && peList.size() > 0 ? peList.get(1) : null;


                if (highestCEOI != null) {
                    dailyOpenInterestPCR.setCallLtp(highestCEOI.getUnderlyingValue());
                    dailyOpenInterestPCR.setCallStrikePrice(highestCEOI.getStrikePrice());
                    //dailyOpenInterestPCR.setMaxCallChangeOI(highestCEOI.getChange());
                    //dailyOpenInterestPCR.setMaxCallOI(highestCEOI.getOpenInterest());
                    //dailyOpenInterestPCR.setMaxCallVolume(highestCEOI.getTotalTradedVolume());
                    dailyOpenInterestPCR.setFirstResistance(highestCEOI.getStrikePrice());
                    dailyOpenInterestPCR.setSecondResistance(secondHighestCEOI.getStrikePrice());
                    dailyOpenInterestPCR.setCallAskPrice(highestCEOI.getAskPrice());
                }

                if (highestPEOI != null) {
                    dailyOpenInterestPCR.setPutLtp(highestPEOI.getUnderlyingValue());
                    dailyOpenInterestPCR.setPutStrikePrice(highestPEOI.getStrikePrice());
                    //dailyOpenInterestPCR.setMaxPutChangeOI(highestPEOI.getChange());
                    //dailyOpenInterestPCR.setMaxPutOI(highestPEOI.getOpenInterest());
                    //dailyOpenInterestPCR.setMaxPutVolume(highestPEOI.getTotalTradedVolume());

                    dailyOpenInterestPCR.setFirstSupport(highestPEOI.getStrikePrice());
                    dailyOpenInterestPCR.setSecondSupport(secondHighestPEOI.getStrikePrice());
                    dailyOpenInterestPCR.setPutAskPrice(highestPEOI.getAskPrice());
                }

                String strategy = findStrategyBasedOnPcrRatio(totOiPcr, dailyOpenInterestPCR);
                dailyOpenInterestPCR.setMarket(strategy);
            }

            //Document document = createDocument(dailyOpenInterestPCR, symbol);
            //collection.insertOne(document);
        }

        return dailyOpenInterestPCR;
    }

    private String findStrategyBasedOnPcrRatio(double totOiPcr, DailyOpenInterestPCR dailyOpenInterestPCR) {
        StringBuilder strategy = new StringBuilder();

        int hedgeDiff = (int)dailyOpenInterestPCR.getCallAskPrice() + (int)dailyOpenInterestPCR.getPutAskPrice();

        if (totOiPcr <= 0.75) {
            // market is bullish
            strategy.append("outcome :: > market is bullish based on PCR ration \n");
        } else if (totOiPcr >= 1.75) {
            //market is bearish
            strategy.append("outcome :: > market is neutral based on PCR ration \n");
        } else {
            //market is neutral
            strategy.append("outcome :: > market is neutral based on PCR ration \n");
        }

        //build strategy
        strategy.append("*************** TRADE SETUP ************************* \n");
        strategy.append(" SELL " + dailyOpenInterestPCR.getFirstResistance() + "\n");
        strategy.append(" SELL " + dailyOpenInterestPCR.getFirstSupport() + "\n");
        strategy.append(" BUY CE with legs difference upside " + hedgeDiff + "\n");
        strategy.append(" BUY PE with legs difference downside " + hedgeDiff + "\n");
        //hedge legs

        return strategy.toString();
    }

    private Document createDocument(DailyOpenInterestPCR pcr, String symbol) {
        Document document = new Document();
        document.put("symbol", symbol);
        document.put("currentDateTime", LocalDateTime.now().toString());
        //document.put("expiryDate", pcr.getExpiryDate());
        //document.put("maxCallOI", pcr.getMaxCallOI());
        //document.put("maxCallChangeOI", pcr.getMaxCallChangeOI());
        //document.put("maxCallVolume", pcr.getMaxCallVolume());
        //document.put("callLtp", pcr.getCallLtp());
        document.put("callStrikePrice", pcr.getCallStrikePrice());
        document.put("resistance", pcr.getFirstResistance());
        //document.put("maxPutOI", pcr.getMaxPutOI());
        //document.put("maxPutChangeOI", pcr.getMaxPutChangeOI());
        //document.put("maxPutVolume", pcr.getMaxPutVolume());
        //document.put("putLtp", pcr.getPutLtp());
        document.put("putStrikePrice", pcr.getPutStrikePrice());
        document.put("support", pcr.getFirstSupport());
        document.put("pcrOI", pcr.getPcrOI());
        //document.put("pcrVolume", pcr.getPcrVolume());
        return document;
    }
}
