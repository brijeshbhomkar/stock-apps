package com.data.service.dataservice.json;

import java.io.Serializable;

import com.data.service.dataservice.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ohlc implements Serializable {

	private static final long serialVersionUID = -9203025149233793325L;
	private String time;
	private long open;
	private long high;
	private long low;
	private long close;
	private long volume;

	public long getOpen() {
		return open;
	}

	public void setOpen(long open) {
		this.open = open;
	}

	public long getHigh() {
		return high;
	}

	public void setHigh(long high) {
		this.high = high;
	}

	public long getLow() {
		return low;
	}

	public void setLow(long low) {
		this.low = low;
	}

	public long getClose() {
		return close;
	}

	public void setClose(long close) {
		this.close = close;
	}

	public long getVolume() {
		return volume;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = DateUtil.convertToDateString(time); //this is required otherwise data is lost
	}
}
