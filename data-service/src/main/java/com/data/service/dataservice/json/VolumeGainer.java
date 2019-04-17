package com.data.service.dataservice.json;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VolumeGainer implements Serializable {

	private static final long serialVersionUID = -453927105322107909L;

	private String symbol;
	private String name;
	private Double turnoverLakhs;
	private Double weekOneAvgVolume;
	private Double weekTwoAvgVolume;
	private Double weekOneAvgVolumeChange;
	private Double weekTwoAvgVolumeChange;
	private Double turnOverCrs;
	private Double ltp;
	private Double netChangePercentage;

	public String getSymbol() {
		return symbol;
	}

	public String getName() {
		return name;
	}

	public Double getTurnoverLakhs() {
		return turnoverLakhs;
	}

	public Double getWeekOneAvgVolume() {
		return weekOneAvgVolume;
	}

	public Double getWeekTwoAvgVolume() {
		return weekTwoAvgVolume;
	}

	public Double getWeekOneAvgVolumeChange() {
		return weekOneAvgVolumeChange;
	}

	public Double getWeekTwoAvgVolumeChange() {
		return weekTwoAvgVolumeChange;
	}

	public Double getTurnOverCrs() {
		return turnOverCrs;
	}

	public Double getLtp() {
		return ltp;
	}

	public Double getNetChangePercentage() {
		return netChangePercentage;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTurnoverLakhs(Double turnoverLakhs) {
		this.turnoverLakhs = turnoverLakhs;
	}

	public void setWeekOneAvgVolume(Double weekOneAvgVolume) {
		this.weekOneAvgVolume = weekOneAvgVolume;
	}

	public void setWeekTwoAvgVolume(Double weekTwoAvgVolume) {
		this.weekTwoAvgVolume = weekTwoAvgVolume;
	}

	public void setWeekOneAvgVolumeChange(Double weekOneAvgVolumeChange) {
		this.weekOneAvgVolumeChange = weekOneAvgVolumeChange;
	}

	public void setWeekTwoAvgVolumeChange(Double weekTwoAvgVolumeChange) {
		this.weekTwoAvgVolumeChange = weekTwoAvgVolumeChange;
	}

	public void setTurnOverCrs(Double turnOverCrs) {
		this.turnOverCrs = turnOverCrs;
	}

	public void setLtp(Double ltp) {
		this.ltp = ltp;
	}

	public void setNetChangePercentage(Double netChangePercentage) {
		this.netChangePercentage = netChangePercentage;
	}
}
