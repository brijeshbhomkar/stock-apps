package com.stocks.data.access.api.groww.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "STOCK_TICK")
public class StockTick implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String symbol;

    private String timstamp;

    private double open;

    private double high;

    private double low;

    private double close;

    private double volume;
}
