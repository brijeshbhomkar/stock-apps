package com.data.service.dataservice.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STOCK")
public class Stocks implements Serializable {

	private static final long serialVersionUID = 3889068968908161074L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "symbol", unique = true)
	private String symbol;

	@Column(name = "series")
	private String series;
	
	@Column(name = "price")
	private String price;
	
	@Column(name = "chng")
	private String change;
	
	@Column(name = "percentage_change")
	private String perChange;
	
	@Column(name = "prevclose")
	private String prevClose;
	
	@Column(name = "tradedquantity")
	private String tradedQuantity;
	
	@Column(name = "marketcap")
	private String marketCap;
	
	@Column(name = "yearhigh")
	private String yearHigh;
	
	@Column(name = "yearlow")
	private String yearLow;
	
	@Column(name = "sumValue")
	private String sumValue;
	
	@Column(name = "sumQuantity")
	private String sumQuantity;
	
	@Column(name = "finQnty")
	private String finQnty;
	
	@Column(name = "sumfinQnty")
	private String sumfinQnty;
	
	@Column(name ="type")
	private String type;

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

	public void setId(Long id) {
		this.id = id;
	}

	public String getSumValue() {
		return sumValue;
	}

	public String getSumQuantity() {
		return sumQuantity;
	}

	public String getFinQnty() {
		return finQnty;
	}

	public String getSumfinQnty() {
		return sumfinQnty;
	}

	public void setSumValue(String sumValue) {
		this.sumValue = sumValue;
	}

	public void setSumQuantity(String sumQuantity) {
		this.sumQuantity = sumQuantity;
	}

	public Long getId() {
		return id;
	}

	public void setFinQnty(String finQnty) {
		this.finQnty = finQnty;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setSumfinQnty(String sumfinQnty) {
		this.sumfinQnty = sumfinQnty;
	}

}
