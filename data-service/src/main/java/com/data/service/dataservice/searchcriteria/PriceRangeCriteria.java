package com.data.service.dataservice.searchcriteria;

import java.io.Serializable;

public class PriceRangeCriteria implements Serializable {
	
	private static final long serialVersionUID = -1366616969225098577L;

	private String symbol;
	private String upperBound;
	private String lowerBound;

	public String getSymbol() {
		return symbol;
	}

	public String getUpperBound() {
		return upperBound;
	}

	public String getLowerBound() {
		return lowerBound;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setUpperBound(String upperBound) {
		this.upperBound = upperBound;
	}

	public void setLowerBound(String lowerBound) {
		this.lowerBound = lowerBound;
	}
}
