package com.stocks.oi.document;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class OpenInterestChainDocument {

    private long date;
    private String symbol;
    private Double strikePrice;
    private Double currentPrice;
    private Double oi;
    private String expiry;
    private String type;
    private Double volume;
    private Double changeInOi;
    private Double lastTradedPrice;
    private Double changePerc;

    public OpenInterestChainDocument() {}

    public Double getLastTradedPrice() {
        return lastTradedPrice;
    }

    public Double getChangePerc() {
        return changePerc;
    }

    public String getSymbol() {
        return symbol;
    }

    public Double getChangeInOi() {
        return changeInOi;
    }

    public Double getVolume() {
        return volume;
    }

    public String getType() {
        return type;
    }

    public String getExpiry() {
        return expiry;
    }

    public Double getOi() {
        return oi;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public Double getStrikePrice() {
        return strikePrice;
    }

    public void setLastTradedPrice(Double lastTradedPrice) {
        this.lastTradedPrice = lastTradedPrice;
    }

    public void setChangePerc(Double changePerc) {
        this.changePerc = changePerc;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setStrikePrice(Double strikePrice) {
        this.strikePrice = strikePrice;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setChangeInOi(Double changeInOi) {
        this.changeInOi = changeInOi;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public void setOi(Double oi) {
        this.oi = oi;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }
}
