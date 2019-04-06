package com.data.service.dataservice.util;

public enum NSEStockType {
	NSE_FITY("nseFifty"), NSE_BANK("nsebank"), NSE_FO("nsefo"), NSE_ALL("nseall");

	private String type;

	private NSEStockType() {
	}

	private NSEStockType(final String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
