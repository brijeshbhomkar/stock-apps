package com.algo.trading.utils;

public enum TradeStatus {
	ACTIVE("Active"), SUSPEND("Suspended"), EXPIRED("Expired");

	private String status;

	TradeStatus(final String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
