package com.data.service.dataservice.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = CandlesDeserializer.class)
public class Candles implements Serializable {

	private static final long serialVersionUID = -7196280638065763633L;
	
	@JsonProperty(value="candles")
	private List<Ohlc> candles;
	
	public Candles() {}
	
	public Candles(final List<Ohlc> ohlc) {
		this.candles = ohlc;
	}

	public List<Ohlc> getCandles() {
		return candles;
	}

	public void setCandles(List<Ohlc> candles) {
		this.candles = candles;
	}

}
