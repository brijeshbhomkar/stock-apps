package com.charting.pojo;

import java.io.Serializable;

public class Symbol implements Serializable {

	private static final long serialVersionUID = -3752643643240322276L;

	private Long symbolId;
	private String symbol;
	private String exchange;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	public Long getSymbolId() {
		return symbolId;
	}

	public void setSymbolId(Long symbolId) {
		this.symbolId = symbolId;
	}

}
