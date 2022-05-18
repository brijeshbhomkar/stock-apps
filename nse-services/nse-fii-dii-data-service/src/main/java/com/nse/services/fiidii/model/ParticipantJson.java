package com.nse.services.fiidii.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParticipantJson implements Serializable {
  //  private Date date;
    private String clientType;
    private String futureIndexLong;
    private String futureIndexShort;
    private String futureStockLong;
    private String futureStockShort;
    private String optionIndexCallLong;
    private String optionIndexPutLong;
    private String optionIndexCallShort;
    private String optionIndexPutShort;
    private String optionStockCallLong;
    private String optionStockPutLong;
    private String optionStockCallShort;
    private String optionStockPutShort;
    private String totalLongContracts;
    private String totalShortContracts;
    private double futureIndexLongPer;
    private double futureIndexShortPer;
    private double futureStockLongPer;
    private double futureStockShortPer;
    private double optionIndexCallLongPer;
    private double optionIndexPutLongPer;
    private double optionIndexPutShortPer;
    private double optionIndexCallShortPer;
    private double optionIndexBullishRatio;
    private double optionIndexBearishRatio;
    private double optionIndexRatioDiff;
    private double optionStockCallLongPer;
    private double optionStockPutLongPer;
    private double optionStockCallShortPer;
    private double optionStockPutShortPer;
    private double optionStockBullishRatio;
    private double optionStockBearishRatio;
    private double optionStockRatioDiff;
    private double futureIndexShortLongRatio;
    private double optionIndexBearishByBullishRatio;
    private double futureStockShortLongRatio;
    private double optionStockBearishByBullishRation;
    private double totalShortLongRatio;
}
