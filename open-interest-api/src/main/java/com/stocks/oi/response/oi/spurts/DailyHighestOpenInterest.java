package com.stocks.oi.response.oi.spurts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyHighestOpenInterest implements Serializable {

    @JsonProperty(value = "symbol")
    private String symbol;

    @JsonProperty(value = "latestOI")
    private String latestOI;

    @JsonProperty(value = "prevOI")
    private String prevOI;

    @JsonProperty(value = "oiChange")
    private String oiChange;

    @JsonProperty(value = "percOIchange")
    private String percOIchange;

    @JsonProperty(value = "volume")
    private String volume;

    @JsonProperty(value = "valueInLakhs")
    private String valueInLakhs;

    @JsonProperty(value = "underlying")
    private String underlying;

    @JsonProperty(value = "isFO")
    private String isFO;

    @JsonProperty(value = "FUTVAL")
    private String futVal;

    @JsonProperty(value = "OPTVAL")
    private String optVal;

    @JsonProperty(value = "TOTVAL")
    private String totVal;

    @JsonProperty(value = "OPVAL")
    private String opVal;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getLatestOI() {
        return latestOI;
    }

    public void setLatestOI(String latestOI) {
        this.latestOI = latestOI;
    }

    public String getPrevOI() {
        return prevOI;
    }

    public void setPrevOI(String prevOI) {
        this.prevOI = prevOI;
    }

    public String getFutVal() {
        return futVal;
    }

    public void setFutVal(String futVal) {
        this.futVal = futVal;
    }

    public String getOptVal() {
        return optVal;
    }

    public void setOptVal(String optVal) {
        this.optVal = optVal;
    }

    public String getTotVal() {
        return totVal;
    }

    public void setTotVal(String totVal) {
        this.totVal = totVal;
    }

    public String getOpVal() {
        return opVal;
    }

    public void setOpVal(String opVal) {
        this.opVal = opVal;
    }

    public String getOiChange() {
        return oiChange;
    }

    public void setOiChange(String oiChange) {
        this.oiChange = oiChange;
    }

    public String getPercOIchange() {
        return percOIchange;
    }

    public void setPercOIchange(String percOIchange) {
        this.percOIchange = percOIchange;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getValueInLakhs() {
        return valueInLakhs;
    }

    public void setValueInLakhs(String valueInLakhs) {
        this.valueInLakhs = valueInLakhs;
    }

    public String getUnderlying() {
        return underlying;
    }

    public void setUnderlying(String underlying) {
        this.underlying = underlying;
    }

    public String getIsFO() {
        return isFO;
    }

    public void setIsFO(String isFO) {
        this.isFO = isFO;
    }


}

