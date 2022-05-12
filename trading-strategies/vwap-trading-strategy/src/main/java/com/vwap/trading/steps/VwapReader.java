package com.vwap.trading.steps;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.vwap.trading.jsons.Candles;

@Component
public class VwapReader implements Serializable {

	private static final long serialVersionUID = 2728092527624845937L;
	
	private long id;
	private String symbolId;
	private String symbolName;
	private Candles candles;

	public String getSymbolName() {
		return symbolName;
	}

	public void setSymbolName(String symbolName) {
		this.symbolName = symbolName;
	}

	public Candles getCandles() {
		return candles;
	}

	public void setCandles(Candles candles) {
		this.candles = candles;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSymbolId() {
		return symbolId;
	}

	public void setSymbolId(String symbolId) {
		this.symbolId = symbolId;
	}

}
