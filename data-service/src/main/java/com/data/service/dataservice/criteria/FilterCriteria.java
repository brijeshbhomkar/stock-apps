package com.data.service.dataservice.criteria;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FilterCriteria implements Serializable {

	private static final long serialVersionUID = 3340078068908646852L;
	private String open;
	private String close;
	private String deviation;

	public String getOpen() {
		return open;
	}

	public String getClose() {
		return close;
	}

	public String getDeviation() {
		return deviation;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public void setClose(String close) {
		this.close = close;
	}

	public void setDeviation(String deviation) {
		this.deviation = deviation;
	}
}
