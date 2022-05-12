package com.stocks.oi.document;

import java.util.Date;

public class DailySlideInPriceRiseInOIDocument implements OpenInterestDocument {

    private String symbol;
    private String instrument;
    private Date expiry;
    private double strike;
    private String optionType;
    private double ltp;
    private double prevClose;
    private double percLtpChange;
    private double latestOI;
    private double previousOI;
    private double oiChange;
    private double volume;
    private double valueInCrores;
    private double premValueInCrores;
    private double underlyValue;
    private double isFO;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public double getStrike() {
        return strike;
    }

    public void setStrike(double strike) {
        this.strike = strike;
    }

    public String getOptionType() {
        return optionType;
    }

    public void setOptionType(String optionType) {
        this.optionType = optionType;
    }

    public double getLtp() {
        return ltp;
    }

    public void setLtp(double ltp) {
        this.ltp = ltp;
    }

    public double getPrevClose() {
        return prevClose;
    }

    public void setPrevClose(double prevClose) {
        this.prevClose = prevClose;
    }

    public double getPercLtpChange() {
        return percLtpChange;
    }

    public void setPercLtpChange(double percLtpChange) {
        this.percLtpChange = percLtpChange;
    }

    public double getLatestOI() {
        return latestOI;
    }

    public void setLatestOI(double latestOI) {
        this.latestOI = latestOI;
    }

    public double getPreviousOI() {
        return previousOI;
    }

    public void setPreviousOI(double previousOI) {
        this.previousOI = previousOI;
    }

    public double getOiChange() {
        return oiChange;
    }

    public void setOiChange(double oiChange) {
        this.oiChange = oiChange;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getValueInCrores() {
        return valueInCrores;
    }

    public void setValueInCrores(double valueInCrores) {
        this.valueInCrores = valueInCrores;
    }

    public double getPremValueInCrores() {
        return premValueInCrores;
    }

    public void setPremValueInCrores(double premValueInCrores) {
        this.premValueInCrores = premValueInCrores;
    }

    public double getUnderlyValue() {
        return underlyValue;
    }

    public void setUnderlyValue(double underlyValue) {
        this.underlyValue = underlyValue;
    }

    public double getIsFO() {
        return isFO;
    }

    public void setIsFO(double isFO) {
        this.isFO = isFO;
    }
}
