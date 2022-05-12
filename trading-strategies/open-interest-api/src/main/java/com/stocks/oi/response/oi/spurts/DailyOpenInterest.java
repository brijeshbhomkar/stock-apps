package com.stocks.oi.response.oi.spurts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyOpenInterest implements Serializable {

    @JsonProperty(value = "symbol")
    private String symbol;

    @JsonProperty(value = "instrument")
    private String instrument;

    @JsonProperty(value = "expiry")
    private String expiry;

    @JsonProperty(value = "strike")
    private String strike;

    @JsonProperty(value = "optionType")
    private String optionType;

    @JsonProperty(value = "ltp")
    private String ltp;

    @JsonProperty(value = "prevClose")
    private String prevClose;

    @JsonProperty(value = "percLtpChange")
    private String percLtpChange;

    @JsonProperty(value = "latestOI")
    private String latestOI;

    @JsonProperty(value = "previousOI")
    private String previousOI;

    @JsonProperty(value = "oiChange")
    private String oiChange;

    @JsonProperty(value = "volume")
    private String volume;

    @JsonProperty(value = "valueInCrores")
    private String valueInCrores;

    @JsonProperty(value = "premValueInCrores")
    private String premValueInCrores;

    @JsonProperty(value = "underlyValue")
    private String underlyValue;

    @JsonProperty(value = "isFO")
    private String isFO;

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

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getStrike() {
        return strike;
    }

    public void setStrike(String strike) {
        this.strike = strike;
    }

    public String getOptionType() {
        return optionType;
    }

    public void setOptionType(String optionType) {
        this.optionType = optionType;
    }

    public String getLtp() {
        return ltp;
    }

    public void setLtp(String ltp) {
        this.ltp = ltp;
    }

    public String getPrevClose() {
        return prevClose;
    }

    public void setPrevClose(String prevClose) {
        this.prevClose = prevClose;
    }

    public String getPercLtpChange() {
        return percLtpChange;
    }

    public void setPercLtpChange(String percLtpChange) {
        this.percLtpChange = percLtpChange;
    }

    public String getLatestOI() {
        return latestOI;
    }

    public void setLatestOI(String latestOI) {
        this.latestOI = latestOI;
    }

    public String getPreviousOI() {
        return previousOI;
    }

    public void setPreviousOI(String previousOI) {
        this.previousOI = previousOI;
    }

    public String getOiChange() {
        return oiChange;
    }

    public void setOiChange(String oiChange) {
        this.oiChange = oiChange;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getValueInCrores() {
        return valueInCrores;
    }

    public void setValueInCrores(String valueInCrores) {
        this.valueInCrores = valueInCrores;
    }

    public String getPremValueInCrores() {
        return premValueInCrores;
    }

    public void setPremValueInCrores(String premValueInCrores) {
        this.premValueInCrores = premValueInCrores;
    }

    public String getUnderlyValue() {
        return underlyValue;
    }

    public void setUnderlyValue(String underlyValue) {
        this.underlyValue = underlyValue;
    }

    public String getIsFO() {
        return isFO;
    }

    public void setIsFO(String isFO) {
        this.isFO = isFO;
    }
}

