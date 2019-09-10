package com.data.service.dataservice.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PreOpen implements Serializable {

	private static final long serialVersionUID = 3497016924530886177L;

	private String symbol;
	private String series;
	private Double price;
	private Double change;
	private Double perChange;
	private Double prevClose;
	private Double tradedQuantity;
	private Double marketCap;
	private Double yearHigh;
	private Double yearLow;
	private Double sumValue;
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
}
