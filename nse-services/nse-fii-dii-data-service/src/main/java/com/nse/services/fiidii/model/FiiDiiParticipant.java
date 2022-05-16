package com.nse.services.fiidii.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class FiiDiiParticipant implements Serializable {
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
