package com.ema.trading.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EMA_15")
public class EMA15 implements Serializable {

	private static final long serialVersionUID = -3495863250483933575L;
	
	@Id
	private String symbolId;
	private String symbolName;
	private String timeframe;
	private double open;
	private double high;
	private double low;
	private double close;
	private double ema15;
	private double ema15prev;
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
	
	public String getTimeframe() {
		return timeframe;
	}

	public void setTimeframe(String timeframe) {
		this.timeframe = timeframe;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public double getEma15() {
		return ema15;
	}

	public void setEma15(double ema15) {
		this.ema15 = ema15;
	}

	public double getEma15prev() {
		return ema15prev;
	}

	public void setEma15prev(double ema15prev) {
		this.ema15prev = ema15prev;
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
