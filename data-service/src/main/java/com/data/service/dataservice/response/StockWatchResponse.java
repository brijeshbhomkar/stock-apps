package com.data.service.dataservice.response;

import java.io.Serializable;
import java.util.List;

import com.data.service.dataservice.json.StockWatch;
import com.data.service.dataservice.response.parsing.StockWatchDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = StockWatchDeserializer.class)
public class StockWatchResponse implements Serializable {

	private static final long serialVersionUID = -3048144134106853098L;

	private Double declines;
	private List<StockWatch> data;

	public StockWatchResponse() {
	}

	public StockWatchResponse(final Double declines, final List<StockWatch> stockWatch) {
		this.declines = declines;
		this.data = stockWatch;
	}

	public Double getDeclines() {
		return declines;
	}

	public List<StockWatch> getData() {
		return data;
	}

	public void setDeclines(Double declines) {
		this.declines = declines;
	}

	public void setData(List<StockWatch> data) {
		this.data = data;
	}
}
