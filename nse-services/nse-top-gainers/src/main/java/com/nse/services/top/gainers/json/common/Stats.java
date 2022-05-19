package com.nse.services.top.gainers.json.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@JsonIgnoreProperties
public class Stats implements Serializable {

    private long ltp;
    private long yearHighPrice;
    private long yearLowPrice;

}
