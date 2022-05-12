package com.stocks.oi.document;

import java.io.Serializable;
import java.time.LocalDate;

public class DailyHighestOpenInterestDocument implements Serializable {
    private String symbol;
    private LocalDate currentMarketDate;
    private double latestOI;
    private double prevOI;
    private double oiChange;
    private double percOIchange;
    private double volume;
    private double valueInLakhs;
    private double underlying;
    private double isFO;
    private double futVal;
    private double optVal;
    private double totVal;

    public LocalDate getCurrentMarketDate() {
        return currentMarketDate;
    }

    public void setCurrentMarketDate(LocalDate currentMarketDate) {
        this.currentMarketDate = currentMarketDate;
    }

    private double opVal;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getLatestOI() {
        return latestOI;
    }

    public void setLatestOI(double latestOI) {
        this.latestOI = latestOI;
    }

    public double getPrevOI() {
        return prevOI;
    }

    public void setPrevOI(double prevOI) {
        this.prevOI = prevOI;
    }

    public double getOiChange() {
        return oiChange;
    }

    public void setOiChange(double oiChange) {
        this.oiChange = oiChange;
    }

    public double getPercOIchange() {
        return percOIchange;
    }

    public void setPercOIchange(double percOIchange) {
        this.percOIchange = percOIchange;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getValueInLakhs() {
        return valueInLakhs;
    }

    public void setValueInLakhs(double valueInLakhs) {
        this.valueInLakhs = valueInLakhs;
    }

    public double getUnderlying() {
        return underlying;
    }

    public void setUnderlying(double underlying) {
        this.underlying = underlying;
    }

    public double getIsFO() {
        return isFO;
    }

    public void setIsFO(double isFO) {
        this.isFO = isFO;
    }

    public double getFutVal() {
        return futVal;
    }

    public void setFutVal(double futVal) {
        this.futVal = futVal;
    }

    public double getOptVal() {
        return optVal;
    }

    public void setOptVal(double optVal) {
        this.optVal = optVal;
    }

    public double getTotVal() {
        return totVal;
    }

    public void setTotVal(double totVal) {
        this.totVal = totVal;
    }

    public double getOpVal() {
        return opVal;
    }

    public void setOpVal(double opVal) {
        this.opVal = opVal;
    }
}
