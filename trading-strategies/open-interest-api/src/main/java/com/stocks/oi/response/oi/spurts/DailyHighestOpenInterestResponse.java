package com.stocks.oi.response.oi.spurts;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class DailyHighestOpenInterestResponse implements Serializable {

    @JsonProperty(value = "time")
    private String time;

    @JsonProperty(value = "data")
    private List<DailyHighestOpenInterest> data;

    @JsonProperty(value = "currentMarketDate")
    private String currentMarketDate;

    @JsonProperty(value = "previousMarketDate")
    private String previousMarketDate;

    public String getTime() {
        return time;
    }

    public List<DailyHighestOpenInterest> getData() {
        return data;
    }

    public void setData(List<DailyHighestOpenInterest> data) {
        this.data = data;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCurrentMarketDate() {
        return currentMarketDate;
    }

    public void setCurrentMarketDate(String currentMarketDate) {
        this.currentMarketDate = currentMarketDate;
    }

    public String getPreviousMarketDate() {
        return previousMarketDate;
    }

    public void setPreviousMarketDate(String previousMarketDate) {
        this.previousMarketDate = previousMarketDate;
    }
}
