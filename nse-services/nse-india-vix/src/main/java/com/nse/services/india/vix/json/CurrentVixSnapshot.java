package com.nse.services.india.vix.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentVixSnapshot implements Serializable {

    @JsonProperty(value = "OI_TIMESTAMP")
    private String timestamp;

    @JsonProperty(value = "CURRENT_PRICE")
    private double currentPrice;

    @JsonProperty(value = "OPEN_PRICE")
    private double openPrice;

    @JsonProperty(value = "HIGH_PRICE")
    private double highPrice;

    @JsonProperty(value = "LOW_PRICE")
    private double lowPrice;

    @JsonProperty(value = "PREV_CLOSE")
    private double prevClose;

    @JsonProperty(value = "PERC_CHANGE")
    private double perChange;
}
