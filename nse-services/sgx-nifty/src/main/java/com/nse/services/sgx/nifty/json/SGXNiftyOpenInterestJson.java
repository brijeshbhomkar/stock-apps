package com.nse.services.sgx.nifty.json;

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
public class SGXNiftyOpenInterestJson implements Serializable {

    @JsonProperty(value = "total-volume")
    private long totalVolume;

    @JsonProperty(value = "record-date")
    private Date date;

    @JsonProperty(value = "open-interest")
    private long openInterest;
}
