package com.vwap.trading.jsons;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vwap.trading.utils.DateUtil;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Candle implements Serializable {

	private static final long serialVersionUID = -9203025149233793325L;
	private long date;
	private double open;
	private double high;
	private double low;
	private double close;
	private double volume;
	private double down = 0;
	private double up = 0;

	public long getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = DateUtil.convertToDate(date).getTime();
		// LocalDateTime.parse(date, formatter);

	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getDown() {
		return down;
	}

	public void setDown(double down) {
		this.down = down;
	}

	public double getUp() {
		return up;
	}

	public void setUp(double up) {
		this.up = up;
	}

	@Override
	public String toString() {
		return "Candle [date=" + date + ", open=" + open + ", high=" + high + ", low=" + low + ", close=" + close
				+ ", volume=" + volume + ", down=" + down + ", up=" + up + "]";
	}
}
