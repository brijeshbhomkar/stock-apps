package com.data.service.dataservice.searchcriteria;

import java.io.Serializable;

public class PriceRangeCriteria implements Serializable {

	private static final long serialVersionUID = -1366616969225098577L;

	private String symbol;
	private String price;

	public String getSymbol() {
		return symbol;
	}

	public String getPrice() {
		return price;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
