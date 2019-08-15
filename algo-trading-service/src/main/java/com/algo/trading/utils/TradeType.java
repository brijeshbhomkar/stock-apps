package com.algo.trading.utils;

public enum TradeType {

	BUY("Buy"), SELL("Sell");

	private String type;

	TradeType(final String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
