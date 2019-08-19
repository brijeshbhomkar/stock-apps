package com.algo.trading.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VWAP")
public class VWAP implements Serializable {

	private static final long serialVersionUID = -1883509034226276106L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String symbolId;
	private String symbolName;
	private double high;
	private double low;
	private double close;
	private double typicalValue;
	private double volume;
	private double totalVP;
	private double vwap;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSymbolId() {
		return symbolId;
	}

	public void setSymbolId(String symbolId) {
		this.symbolId = symbolId;
	}

	public String getSymbolName() {
		return symbolName;
	}

	public void setSymbolName(String symbolName) {
		this.symbolName = symbolName;
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

	public double getTypicalValue() {
		return typicalValue;
	}

	public void setTypicalValue(double typicalValue) {
		this.typicalValue = typicalValue;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

	public double getTotalVP() {
		return totalVP;
	}

	public void setTotalVP(double totalVP) {
		this.totalVP = totalVP;
	}

	public double getVwap() {
		return vwap;
	}

	public void setVwap(double vwap) {
		this.vwap = vwap;
	}
}
