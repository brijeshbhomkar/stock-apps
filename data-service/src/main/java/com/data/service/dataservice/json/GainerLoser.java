package com.data.service.dataservice.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GainerLoser implements Serializable, Comparable<GainerLoser> {

	private static final long serialVersionUID = 4248021879092584260L;

	private String symbol;
	private Double ltp;
	private Double perChange;
	private Double tradedQty;
	private Double qtyValueInLacs;
	private Double open;
	private Double high;
	private Double low;
	private Double prev;
	private Double close;

	public String getSymbol() {
		return symbol;
	}

	public Double getLtp() {
		return ltp;
	}

	public Double getPerChange() {
		return perChange;
	}

	public Double getTradedQty() {
		return tradedQty;
	}

	public Double getQtyValueInLacs() {
		return qtyValueInLacs;
	}

	public Double getOpen() {
		return open;
	}

	public Double getHigh() {
		return high;
	}

	public Double getLow() {
		return low;
	}

	public Double getPrev() {
		return prev;
	}

	public Double getClose() {
		return close;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setLtp(Double ltp) {
		this.ltp = ltp;
	}

	public void setPerChange(Double perChange) {
		this.perChange = perChange;
	}

	public void setTradedQty(Double tradedQty) {
		this.tradedQty = tradedQty;
	}

	public void setQtyValueInLacs(Double qtyValueInLacs) {
		this.qtyValueInLacs = qtyValueInLacs;
	}

	public void setOpen(Double open) {
		this.open = open;
	}

	public void setHigh(Double high) {
		this.high = high;
	}

	public void setLow(Double low) {
		this.low = low;
	}

	public void setPrev(Double prev) {
		this.prev = prev;
	}

	public void setClose(Double close) {
		this.close = close;
	}

	@Override
	public int compareTo(GainerLoser o) {
		return ltp.compareTo(o.getLtp());
	}
}
