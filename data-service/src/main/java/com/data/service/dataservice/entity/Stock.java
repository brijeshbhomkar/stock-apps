package com.data.service.dataservice.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STOCKS")
public class Stock implements Serializable {

	private static final long serialVersionUID = 3889068968908161074L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "symbol")
	private String symbol;
	@Column(name = "series")
	private String series;
	@Column(name = "price")
	private Long price;
	@Column(name = "change")
	private Long change;
	@Column(name = "percentage_change")
	private Long perChange;
	@Column(name = "prev_close")
	private Long prevClose;
	@Column(name = "quantity")
	private Long quantity;
	@Column(name = "traded_quantity")
	private Long tradedQuantity;
	@Column(name = "market_cap")
	private Long marketCap;
	@Column(name = "year_high")
	private Long yearHigh;
	@Column(name = "year_low")
	private Long yearLow;

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

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getChange() {
		return change;
	}

	public void setChange(Long change) {
		this.change = change;
	}

	public Long getPerChange() {
		return perChange;
	}

	public void setPerChange(Long perChange) {
		this.perChange = perChange;
	}

	public Long getPrevClose() {
		return prevClose;
	}

	public void setPrevClose(Long prevClose) {
		this.prevClose = prevClose;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Long getTradedQuantity() {
		return tradedQuantity;
	}

	public void setTradedQuantity(Long tradedQuantity) {
		this.tradedQuantity = tradedQuantity;
	}

	public Long getMarketCap() {
		return marketCap;
	}

	public void setMarketCap(Long marketCap) {
		this.marketCap = marketCap;
	}

	public Long getYearHigh() {
		return yearHigh;
	}

	public void setYearHigh(Long yearHigh) {
		this.yearHigh = yearHigh;
	}

	public Long getYearLow() {
		return yearLow;
	}

	public void setYearLow(Long yearLow) {
		this.yearLow = yearLow;
	}

}
