package com.data.service.dataservice.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StockWatch implements Serializable, Comparable<StockWatch> {

	private static final long serialVersionUID = 8150197359434277644L;

	private String symbol;
	private Double open;
	private Double high;
	private Double low;
	private Double ltP;
	private Double ptsC;
	private Double per;
	private Double trdVol;
	private Double trdVolM;
	private Double ntP;
	private Double mVal;
	private Double wkhi;
	private Double wklo;
	private Double previousClose;
	private Double dayEndClose;
	private Double iislPtsChange;
	private Double iislPercChange;

	public String getSymbol() {
		return symbol;
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

	public Double getLtP() {
		return ltP;
	}

	public Double getPtsC() {
		return ptsC;
	}

	public Double getPer() {
		return per;
	}

	public Double getTrdVol() {
		return trdVol;
	}

	public Double getTrdVolM() {
		return trdVolM;
	}

	public Double getNtP() {
		return ntP;
	}

	public Double getmVal() {
		return mVal;
	}

	public Double getWkhi() {
		return wkhi;
	}

	public Double getWklo() {
		return wklo;
	}

	public Double getPreviousClose() {
		return previousClose;
	}

	public Double getDayEndClose() {
		return dayEndClose;
	}

	public Double getIislPtsChange() {
		return iislPtsChange;
	}

	public Double getIislPercChange() {
		return iislPercChange;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
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

	public void setLtP(Double ltP) {
		this.ltP = ltP;
	}

	public void setPtsC(Double ptsC) {
		this.ptsC = ptsC;
	}

	public void setPer(Double per) {
		this.per = per;
	}

	public void setTrdVol(Double trdVol) {
		this.trdVol = trdVol;
	}

	public void setTrdVolM(Double trdVolM) {
		this.trdVolM = trdVolM;
	}

	public void setNtP(Double ntP) {
		this.ntP = ntP;
	}

	public void setmVal(Double mVal) {
		this.mVal = mVal;
	}

	public void setWkhi(Double wkhi) {
		this.wkhi = wkhi;
	}

	public void setWklo(Double wklo) {
		this.wklo = wklo;
	}

	public void setPreviousClose(Double previousClose) {
		this.previousClose = previousClose;
	}

	public void setDayEndClose(Double dayEndClose) {
		this.dayEndClose = dayEndClose;
	}

	public void setIislPtsChange(Double iislPtsChange) {
		this.iislPtsChange = iislPtsChange;
	}

	public void setIislPercChange(Double iislPercChange) {
		this.iislPercChange = iislPercChange;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((low == null) ? 0 : low.hashCode());
		result = prime * result + ((open == null) ? 0 : open.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockWatch other = (StockWatch) obj;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}

	@Override
	public int compareTo(StockWatch o) {
		return trdVol.compareTo(o.trdVol);
	}

}
