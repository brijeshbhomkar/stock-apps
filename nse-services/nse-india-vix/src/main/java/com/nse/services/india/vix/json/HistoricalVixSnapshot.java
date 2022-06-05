package com.nse.services.india.vix.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoricalVixSnapshot implements Serializable {

    @JsonProperty(value = "currentPrice")
    private double currentPrice;

    @JsonProperty(value = "currentDate")
    private String currentDate;

    @JsonProperty(value = "highPrice")
    private double highPrice;

    @JsonProperty(value = "lowPrice")
    private double lowPrice;

    @JsonProperty(value = "highPriceDate")
    private String highPriceDate;

    @JsonProperty(value = "lowPriceDate")
    private String lowPriceDate;

    @JsonProperty(value = "prevPrice")
    private double prevPrice;

    @JsonProperty(value = "prevDate")
    private String prevDate;

    @JsonProperty(value = "weekAgoPrice")
    private double weekAgoPrice;

    @JsonProperty(value = "weekAgoDate")
    private String weekAgoDate;

    @JsonProperty(value = "monthAgoPrice")
    private double monthAgoPrice;

    @JsonProperty(value = "monthAgoDate")
    private String monthAgoDate;

    @JsonProperty(value = "yearAgoPrice")
    private double yearAgoPrice;

    @JsonProperty(value = "yearAgoDate")
    private String yearAgoDate;
}
