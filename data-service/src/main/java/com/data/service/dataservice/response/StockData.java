package com.data.service.dataservice.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder
public class StockData implements Serializable {

	private static final long serialVersionUID = 3497016924530886177L;

	private String symbol;
	private String series;
	private String price;
	private String change;
	private String perChange;
	private String prevClose;
	private String tradedQuantity;
	private String marketCap;
	private String yearHigh;
	private String yearLow;
	private String sumValue;
	private String sumQuantity;
	private String finQnty;
	private String sumfinQnty;
	
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getChange() {
		return change;
	}
	public void setChange(String change) {
		this.change = change;
	}
	public String getPerChange() {
		return perChange;
	}
	public void setPerChange(String perChange) {
		this.perChange = perChange;
	}
	public String getPrevClose() {
		return prevClose;
	}
	public void setPrevClose(String prevClose) {
		this.prevClose = prevClose;
	}
	public String getTradedQuantity() {
		return tradedQuantity;
	}
	public void setTradedQuantity(String tradedQuantity) {
		this.tradedQuantity = tradedQuantity;
	}
	public String getMarketCap() {
		return marketCap;
	}
	public void setMarketCap(String marketCap) {
		this.marketCap = marketCap;
	}
	public String getYearHigh() {
		return yearHigh;
	}
	public void setYearHigh(String yearHigh) {
		this.yearHigh = yearHigh;
	}
	public String getYearLow() {
		return yearLow;
	}
	public void setYearLow(String yearLow) {
		this.yearLow = yearLow;
	}
	public String getSumValue() {
		return sumValue;
	}
	public void setSumValue(String sumValue) {
		this.sumValue = sumValue;
	}
	public String getSumQuantity() {
		return sumQuantity;
	}
	public void setSumQuantity(String sumQuantity) {
		this.sumQuantity = sumQuantity;
	}
	public String getFinQnty() {
		return finQnty;
	}
	public void setFinQnty(String finQnty) {
		this.finQnty = finQnty;
	}
	public String getSumfinQnty() {
		return sumfinQnty;
	}
	public void setSumfinQnty(String sumfinQnty) {
		this.sumfinQnty = sumfinQnty;
	}

	
}