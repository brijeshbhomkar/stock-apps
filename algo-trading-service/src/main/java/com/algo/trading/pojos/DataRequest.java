package com.algo.trading.pojos;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class DataRequest implements Serializable {

	private static final long serialVersionUID = -7090018390967227392L;

	private String symbol;
	private String userId;
	private String timeframe;
	private String fromDate;
	private String toDate;

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

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

}
