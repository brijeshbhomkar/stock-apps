package com.stocks.oi.response.oi.chain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenInterestChainRecords implements Serializable {

    @JsonProperty(value = "expiryDates")
    private List<String> expiryDates;

    @JsonProperty(value = "data")
    private List<OpenInterestChain> data;

    @JsonProperty(value = "timestamp")
    private String timestamp;

    @JsonProperty(value = "underlyingValue")
    private String underlyingValue;

    @JsonProperty(value = "strikePrices")
    private List<Integer> strikePrices;

    public List<Integer> getStrikePrices() {
        return strikePrices;
    }

    public void setUnderlyingValue(String underlyingValue) {
        this.underlyingValue = underlyingValue;
    }

    public List<OpenInterestChain> getData() {
        return data;
    }

    public List<String> getExpiryDates() {
        return expiryDates;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getUnderlyingValue() {
        return underlyingValue;
    }

    public void setData(List<OpenInterestChain> data) {
        this.data = data;
    }

    public void setExpiryDates(List<String> expiryDates) {
        this.expiryDates = expiryDates;
    }

    public void setStrikePrices(List<Integer> strikePrices) {
        this.strikePrices = strikePrices;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
