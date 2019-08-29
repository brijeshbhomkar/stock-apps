package com.ema.trading.jsons;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = CandlesDeserializer.class)
public class Candles implements Serializable {

	private static final long serialVersionUID = -7196280638065763633L;
	
	@JsonProperty(value="candles")
	private List<Candle> candles;
	
	public Candles() {}
	
	public Candles(final List<Candle> ohlc) {
		this.candles = ohlc;
	}

	public List<Candle> getCandles() {
		return candles;
	}

	public void setCandles(List<Candle> candles) {
		this.candles = candles;
	}

}
