package com.ema.trading.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SYMBOL")
public class Symbol implements Serializable {

	private static final long serialVersionUID = -7462714771560213319L;

	@Id
	private Long symbolId;
	private String symbol;
	private String exchange;
	private String userId;
	private String timeframe;

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTimeframe() {
		return timeframe;
	}

	public void setTimeframe(String timeframe) {
		this.timeframe = timeframe;
	}

}
