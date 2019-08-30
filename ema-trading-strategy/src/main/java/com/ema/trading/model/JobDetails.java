package com.ema.trading.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "JOB_DETAILS")
public class JobDetails implements Serializable {

	private static final long serialVersionUID = -53631738251432721L;

	private String symbol;
	@Id
	private long symbolId;
	private String userId;
	private String timeframe;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
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

	public long getSymbolId() {
		return symbolId;
	}

	public void setSymbolId(long symbolId) {
		this.symbolId = symbolId;
	}
}
