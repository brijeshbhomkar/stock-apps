package com.data.service.dataservice.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = StockDataDeserializer.class)
public class StockDataResponse implements Serializable {
	private static final long serialVersionUID = -849223705162170891L;

	private long declines;
	private long noChange;
	private List<StockData> data;

	public StockDataResponse() {
	}

	public StockDataResponse(final long declines, final long noChange, final List<StockData> data) {
		this.data = data;
		this.declines = declines;
		this.noChange = noChange;
	}

	public long getDeclines() {
		return declines;
	}

	public void setDeclines(long declines) {
		this.declines = declines;
	}

	public long getNoChange() {
		return noChange;
	}

	public void setNoChange(long noChange) {
		this.noChange = noChange;
	}

	public List<StockData> getData() {
		return data;
	}

	public void setData(List<StockData> data) {
		this.data = data;
	}

}
