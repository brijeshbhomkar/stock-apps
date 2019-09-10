package com.data.service.dataservice.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataSearchCriteria implements Serializable {

	private static final long serialVersionUID = -8694531932924646636L;

	private String kiteId;
	private String startDate;
	private String endDate;
	private String symbol;
	private String period;

	public String getKiteId() {
		return kiteId;
	}

	public void setKiteId(String kiteId) {
		this.kiteId = kiteId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

}
