package com.algo.trading.jsons;

import java.io.Serializable;

import com.algo.trading.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Candle implements Serializable {

	private static final long serialVersionUID = -9203025149233793325L;
	private double date;
	private double open;
	private double high;
	private double low;
	private double close;
	private double volume;
	private double down = 0;
	private double up = 0;

	public double getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = DateUtil.convertToDate(date).getTime();
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
