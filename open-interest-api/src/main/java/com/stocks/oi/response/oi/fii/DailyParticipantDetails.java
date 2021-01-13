package com.stocks.oi.response.oi.fii;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyParticipantDetails implements Serializable {

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
}
