package com.data.service.dataservice.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VOLUME_GAINER")
public class VolumeGainerData implements Serializable {

	private static final long serialVersionUID = -5800297441629302305L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "symbol")
	private String symbol;

	@Column(name = "name")
	private String name;

	@Column(name = "turn_over_lacs")
	private Double turnoverLakhs;

	@Column(name = "week1Avg_volume")
	private Double weekOneAvgVolume;

	@Column(name = "week2Avg_volume")
	private Double weekTwoAvgVolume;

	@Column(name = "week1Avg_volume_change")
	private Double weekOneAvgVolumeChange;

	@Column(name = "week2Avg_volume_change")
	private Double weekTwoAvgVolumeChange;

	@Column(name = "turn_over_crs")
	private Double turnOverCrs;

	@Column(name = "ltp")
	private Double ltp;

	@Column(name = "net_change_percent")
	private Double netChangePercentage;

	public Long getId() {
		return id;
	}

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

	public void setId(Long id) {
		this.id = id;
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
