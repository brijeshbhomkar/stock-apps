package com.data.service.dataservice.technicalanalysis;

import java.io.Serializable;

public class TechnicalAnalysis implements Serializable {
	private static final long serialVersionUID = -5897801173543319961L;
	
	private String id;
	private String symbol;
	private String maType;
	private String startDate;
	private String endDate;

	public String getSymbol() {
		return symbol;
	}

	public String getMaType() {
		return maType;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setMaType(String maType) {
		this.maType = maType;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
