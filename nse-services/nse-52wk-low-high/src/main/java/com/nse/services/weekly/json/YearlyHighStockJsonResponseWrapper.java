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
public class YearlyHighStockJsonResponseWrapper implements Serializable {

    @JsonProperty(value = "categoryResponseMap")
    private YearlyHighStockJsonResponse yearlyHighStockJsonResponse;

    @JsonProperty(value = "timeStamp")
    private long timeStamp;

}
