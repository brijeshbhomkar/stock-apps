package com.nse.common.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Stats implements Serializable {

    private long ltp;
    private long yearHighPrice;
    private long yearLowPrice;

}
