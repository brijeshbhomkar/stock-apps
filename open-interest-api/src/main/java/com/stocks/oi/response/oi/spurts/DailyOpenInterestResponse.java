package com.stocks.oi.response.oi.spurts;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class DailyOpenInterestResponse implements Serializable {

    @JsonProperty(value = "time")
    private String time;

    @JsonProperty(value = "data")
    private List<DailyOpenInterest> data;

    @JsonProperty(value = "currentMarketDate")
    private String currentMarketDate;

    @JsonProperty(value = "previousMarketDate")
    private String previousMarketDate;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<DailyOpenInterest> getData() {
        return data;
    }

    public String getCurrentMarketDate() {
        return currentMarketDate;
    }

    public String getPreviousMarketDate() {
        return previousMarketDate;
    }

    public void setData(List<DailyOpenInterest> data) {
        this.data = data;
    }

    public void setCurrentMarketDate(String currentMarketDate) {
        this.currentMarketDate = currentMarketDate;
    }

    public void setPreviousMarketDate(String previousMarketDate) {
        this.previousMarketDate = previousMarketDate;
    }
}
