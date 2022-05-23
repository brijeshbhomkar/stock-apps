package com.stocks.data.access.api.groww.request;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class StockRequest implements Serializable {
    private String symbol;
    private String type;
    private String interval;
    private int intervalVal;
}
