package com.nse.services.fiidii.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParticipantJson implements Serializable {
    private Date date;
    private String clientType;
    private String futureIndexLong;
    private double futureIndexLongPer;
    private String futureIndexShort;
    private double futureIndexShortPer;
    private String futureStockLong;
    private double futureStockLongPer;
    private String futureStockShort;
    private double futureStockShortPer;
    private String optionIndexCallLong;
    private double optionIndexCallLongPer;
    private String optionIndexPutLong;
    private double optionIndexPutLongPer;
    private String optionIndexCallShort;
    private double optionIndexCallShortPer;
    private String optionIndexPutShort;
    private double optionIndexPutShortPer;
    private String optionStockCallLong;
    private double optionStockCallLongPer;
    private String optionStockPutLong;
    private double optionStockPutLongPer;
    private String optionStockCallShort;
    private double optionStockCallShortPer;
    private String optionStockPutShort;
    private double optionStockPutShortPer;
    private String totalLongContracts;
    private String totalShortContracts;
    private double totalLongShortRatio;
}
