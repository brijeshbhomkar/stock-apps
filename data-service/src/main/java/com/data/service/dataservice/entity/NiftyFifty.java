package com.data.service.dataservice.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NIFTY_FIFTY")
public class NiftyFifty implements Serializable {

	private static final long serialVersionUID = -2562363493751521282L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String symbol;
	private long open;
	private long high;
	private long low;
	private long ltp;
	private long change;
	private long perChange;
	private long volumeInLacs;
	private long turnOverCrs;
	private long fiftyWeekLow;
	private long fiftyWeekHigh;
	private long OneYearPerChange;
	private long monthPerChange;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOpen() {
		return open;
	}

	public void setOpen(long open) {
		this.open = open;
	}

	public long getHigh() {
		return high;
	}

	public void setHigh(long high) {
		this.high = high;
	}

	public long getLow() {
		return low;
	}

	public void setLow(long low) {
		this.low = low;
	}

	public long getLtp() {
		return ltp;
	}

	public void setLtp(long ltp) {
		this.ltp = ltp;
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

	public long getVolumeInLacs() {
		return volumeInLacs;
	}

	public void setVolumeInLacs(long volumeInLacs) {
		this.volumeInLacs = volumeInLacs;
	}

	public long getTurnOverCrs() {
		return turnOverCrs;
	}

	public void setTurnOverCrs(long turnOverCrs) {
		this.turnOverCrs = turnOverCrs;
	}

	public long getFiftyWeekLow() {
		return fiftyWeekLow;
	}

	public void setFiftyWeekLow(long fiftyWeekLow) {
		this.fiftyWeekLow = fiftyWeekLow;
	}

	public long getFiftyWeekHigh() {
		return fiftyWeekHigh;
	}

	public void setFiftyWeekHigh(long fiftyWeekHigh) {
		this.fiftyWeekHigh = fiftyWeekHigh;
	}

	public long getOneYearPerChange() {
		return OneYearPerChange;
	}

	public void setOneYearPerChange(long oneYearPerChange) {
		OneYearPerChange = oneYearPerChange;
	}

	public long getMonthPerChange() {
		return monthPerChange;
	}

	public void setMonthPerChange(long monthPerChange) {
		this.monthPerChange = monthPerChange;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

}
