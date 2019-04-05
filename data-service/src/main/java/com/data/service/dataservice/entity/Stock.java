package com.data.service.dataservice.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STOCK")
public class Stock implements Serializable {

	private static final long serialVersionUID = 3889068968908161074L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String symbol;
	private String series;
	private long price;
	private long change;
	private long perChange;
	private long prevClose;
	private long quantity;
	private long tradedQuantity;
	private long marketCap;
	private long yearHigh;
	private long yearLow;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public long getChange() {
		return change;
	}

	public void setChange(long change) {
		this.change = change;
	}

	public long getPerChange() {
		return perChange;
	}

	public void setPerChange(long perChange) {
		this.perChange = perChange;
	}

	public long getPrevClose() {
		return prevClose;
	}

	public void setPrevClose(long prevClose) {
		this.prevClose = prevClose;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public long getTradedQuantity() {
		return tradedQuantity;
	}

	public void setTradedQuantity(long tradedQuantity) {
		this.tradedQuantity = tradedQuantity;
	}

	public long getMarketCap() {
		return marketCap;
	}

	public void setMarketCap(long marketCap) {
		this.marketCap = marketCap;
	}

	public long getYearHigh() {
		return yearHigh;
	}

	public void setYearHigh(long yearHigh) {
		this.yearHigh = yearHigh;
	}

	public long getYearLow() {
		return yearLow;
	}

	public void setYearLow(long yearLow) {
		this.yearLow = yearLow;
	}

}
