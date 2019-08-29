package com.ema.trading.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EMA implements Serializable {

	private static final long serialVersionUID = -3495863250483933575L;

	@Id
	private String symbolId;

	private String symbolName;
	private double ema8;
	private double ema15;
	private String timeframe;

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

	public double getEma15() {
		return ema15;
	}

	public void setEma15(double ema15) {
		this.ema15 = ema15;
	}

	public String getTimeframe() {
		return timeframe;
	}

	public void setTimeframe(String timeframe) {
		this.timeframe = timeframe;
	}
}
