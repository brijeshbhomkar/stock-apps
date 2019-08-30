package com.ema.trading.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EMA_8")
public class EMA8 implements Serializable {

	private static final long serialVersionUID = -3495863250483933575L;
	
	@Id
	private String symbolId;
	private String symbolName;
	private String timeframe;
	private double open;
	private double high;
	private double low;
	private double close;
	private double ema8;
	private double ema8prev;
	private String timestamp;

	public String getSymbolId() {
		return symbolId;
	}

	public void setSymbolId(String symbolId) {
		this.symbolId = symbolId;
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

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
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
