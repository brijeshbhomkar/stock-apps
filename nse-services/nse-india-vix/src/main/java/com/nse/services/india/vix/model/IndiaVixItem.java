package com.nse.services.india.vix.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IndiaVixItem implements Serializable {
    private Date date;
    private double open;
    private double high;
    private double low;
    private double close;
    private double prevClose;
    private double change;
    private double perChange;

}

