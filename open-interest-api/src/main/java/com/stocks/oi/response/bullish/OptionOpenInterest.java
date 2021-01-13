package com.stocks.oi.response.bullish;

import java.io.Serializable;

public class OptionOpenInterest implements Serializable {

    private double strikePrice;
    private String expiryDate;
    private String optionType;
    private double volume;
    private double oiChange;
    private double latestOI;
    private double currentPrice;
    private String timestamp;
    private Double changeInOpenInterest;
    private Double totalBuyQuantity;
    private Double totalSellQuantity;
    private Double pchangeInOpenInterest;
    private Double bidQty;
    private Double askQty;
    private Double bidPrice;
    private Double askPrice;
    private Double underlyingValue;

    public void setStrikePrice(double strikePrice) {
        this.strikePrice = strikePrice;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void setOiChange(double oiChange) {
        this.oiChange = oiChange;
    }

    public void setOpenInterest(double latestOI) {
        this.latestOI = latestOI;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }


    public void setStrike(Double strike) {
        this.strikePrice = strike;
    }

    public Double getStrikePrice() {
        return strikePrice;
    }

    public void setOptionType(String optionType) {
        this.optionType = optionType;
    }

    public String getOptionType() {
        return optionType;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getVolume() {
        return volume;
    }

    public void setOiChange(Double oiChange) {
        this.oiChange = oiChange;
    }

    public Double getOiChange() {
        return oiChange;
    }

    public void setOpenInterest(Double latestOI) {
        this.latestOI = latestOI;
    }

    public Double getLatestOI() {
        return latestOI;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setChangeInOpenInterest(Double changeInOpenInterest) {
        this.changeInOpenInterest = changeInOpenInterest;
    }

    public Double getChangeInOpenInterest() {
        return changeInOpenInterest;
    }

    public void setTotalBuyQuantity(Double totalBuyQuantity) {
        this.totalBuyQuantity = totalBuyQuantity;
    }

    public Double getTotalBuyQuantity() {
        return totalBuyQuantity;
    }

    public void setTotalSellQuantity(Double totalSellQuantity) {
        this.totalSellQuantity = totalSellQuantity;
    }

    public Double getTotalSellQuantity() {
        return totalSellQuantity;
    }

    public void setPchangeInOpenInterest(Double pchangeInOpenInterest) {
        this.pchangeInOpenInterest = pchangeInOpenInterest;
    }

    public Double getPchangeInOpenInterest() {
        return pchangeInOpenInterest;
    }

    public void setBidQty(Double bidQty) {
        this.bidQty = bidQty;
    }

    public Double getBidQty() {
        return bidQty;
    }

    public void setAskQty(Double askQty) {
        this.askQty = askQty;
    }

    public Double getAskQty() {
        return askQty;
    }

    public void setBidPrice(Double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public Double getBidPrice() {
        return bidPrice;
    }

    public void setAskPrice(Double askPrice) {
        this.askPrice = askPrice;
    }

    public Double getAskPrice() {
        return askPrice;
    }

    public void setUnderlyingValue(Double underlyingValue) {
        this.underlyingValue = underlyingValue;
    }

    public Double getUnderlyingValue() {
        return underlyingValue;
    }
}
