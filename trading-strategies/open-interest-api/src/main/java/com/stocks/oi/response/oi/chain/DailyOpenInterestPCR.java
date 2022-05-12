package com.stocks.oi.response.oi.chain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DailyOpenInterestPCR implements Serializable {

    private String symbol;
    private LocalDateTime currentDateTime;
    //private Date expiryDate;
    //private double maxCallOI;
    //private double maxCallChangeOI;
    //private double maxCallVolume;
    private double callAskPrice;
    private double callLtp;
    private double callStrikePrice;
    private double firstResistance;
    private double secondResistance;
    //private double maxPutOI;
    //private double maxPutChangeOI;
    //private double maxPutVolume;
    private double putLtp;
    private double putStrikePrice;
    private double firstSupport;
    private double secondSupport;
    private double putAskPrice;
    private double pcrOI;
    private double pcrVolume;
    private String market;
}
