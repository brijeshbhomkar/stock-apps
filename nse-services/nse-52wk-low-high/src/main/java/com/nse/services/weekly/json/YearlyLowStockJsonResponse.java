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
@JsonIgnoreProperties("categoryResponseMap")
public class YearlyLowStockJsonResponse implements Serializable {

    @JsonProperty(value = "YEARLY_LOW")
    private YearlyLowStockJson yearlyLowStockJson;

}
