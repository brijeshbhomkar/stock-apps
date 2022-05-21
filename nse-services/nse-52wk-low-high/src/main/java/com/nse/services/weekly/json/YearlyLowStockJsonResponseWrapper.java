package com.nse.services.weekly.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class YearlyLowStockJsonResponseWrapper implements Serializable {

    @JsonProperty(value = "categoryResponseMap")
    private YearlyLowStockJsonResponse yearlyLowStockJsonResponse;

    @JsonProperty(value = "timeStamp")
    private long timeStamp;

}
