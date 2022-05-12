package com.ema.trading.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "EMA_8")
public class EMA8 implements Serializable {

    private static final long serialVersionUID = -3495863250483933575L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String symbol;
    private String symbolName;
    private String timeframe;
    private double open;
    private double high;
    private double low;
    private double close;
    private double ema8;
    private double ema8prev;

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbolName() {
        return symbolName;
    }

    public void setSymbolName(String symbolName) {
        this.symbolName = symbolName;
    }

    public double getEma8() {
        return ema8;
    }

    public void setEma8(double ema8) {
        this.ema8 = ema8;
    }

    public String getTimeframe() {
        return timeframe;
    }

    public void setTimeframe(String timeframe) {
        this.timeframe = timeframe;
    }

    public double getEma8prev() {
        return ema8prev;
    }

    public void setEma8prev(double ema8prev) {
        this.ema8prev = ema8prev;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }
}
