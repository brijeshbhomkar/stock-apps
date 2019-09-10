package com.data.service.dataservice.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PreOpenStock implements Serializable {

	private static final long serialVersionUID = 3889068968908161074L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "symbol", unique = true)
	private String symbol;

	@Column(name = "series")
	private String series;
	
	@Column(name = "price")
	private Double price;
	
	@Column(name = "chng")
	private Double change;
	
	@Column(name = "percentage_change")
	private Double perChange;
	
	@Column(name = "prevclose")
	private Double prevClose;
	
	@Column(name = "tradedquantity")
	private Double tradedQuantity;
	
	@Column(name = "marketcap")
	private Double marketCap;
	
	@Column(name = "yearhigh")
	private Double yearHigh;
	
	@Column(name = "yearlow")
	private Double yearLow;
	
	@Column(name = "sumValue")
	private Double sumValue;
	
	@Column(name ="type")
	private String type;

	public Long getId() {
		return id;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getSeries() {
		return series;
	}

	public Double getPrice() {
		return price;
	}

	public Double getChange() {
		return change;
	}

	public Double getPerChange() {
		return perChange;
	}

	public Double getPrevClose() {
		return prevClose;
	}

	public Double getTradedQuantity() {
		return tradedQuantity;
	}

	public Double getMarketCap() {
		return marketCap;
	}

	public Double getYearHigh() {
		return yearHigh;
	}

	public Double getYearLow() {
		return yearLow;
	}

	public Double getSumValue() {
		return sumValue;
	}

	public String getType() {
		return type;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setChange(Double change) {
		this.change = change;
	}

	public void setPerChange(Double perChange) {
		this.perChange = perChange;
	}

	public void setPrevClose(Double prevClose) {
		this.prevClose = prevClose;
	}

	public void setTradedQuantity(Double tradedQuantity) {
		this.tradedQuantity = tradedQuantity;
	}

	public void setMarketCap(Double marketCap) {
		this.marketCap = marketCap;
	}

	public void setYearHigh(Double yearHigh) {
		this.yearHigh = yearHigh;
	}

	public void setYearLow(Double yearLow) {
		this.yearLow = yearLow;
	}

	public void setSumValue(Double sumValue) {
		this.sumValue = sumValue;
	}
	
	public void setType(String type) {
		this.type = type;
	}

}
