package com.vwap.trading.jsons;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class CandleResponse implements Serializable {

	private static final long serialVersionUID = 3585210773799134755L;
	
	@JsonProperty(value="status")
	private String status;
	
	@JsonProperty(value="data")
	private Candles data;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Candles getData() {
		return data;
	}

	public void setData(Candles data) {
		this.data = data;
	}
}
