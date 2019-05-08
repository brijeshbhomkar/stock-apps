package com.data.service.dataservice.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CANDLE")
public class CandleTick implements Serializable, Comparable<CandleTick> {

	private static final long serialVersionUID = 5954818622290494162L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "SYMBOL")
	private String symbol;

	@Column(name = "TICK_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date tickTime;

	@Column(name = "OPEN")
	private Long open;

	@Column(name = "HIGH")
	private Long high;

	@Column(name = "LOW")
	private Long low;

	@Column(name = "CLOSE")
	private Long close;

	@Column(name = "VOLUME")
	private Long volume;

	@Column(name = "PERIOD")
	private String period;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Date getTickTime() {
		return tickTime;
	}

	public void setTickTime(Date tickTime) {
		this.tickTime = tickTime;
	}

	public Long getOpen() {
		return open;
	}

	public void setOpen(Long open) {
		this.open = open;
	}

	public Long getHigh() {
		return high;
	}

	public void setHigh(Long high) {
		this.high = high;
	}

	public Long getLow() {
		return low;
	}

	public void setLow(Long low) {
		this.low = low;
	}

	public Long getClose() {
		return close;
	}

	public void setClose(Long close) {
		this.close = close;
	}

	public Long getVolume() {
		return volume;
	}

	public void setVolume(Long volume) {
		this.volume = volume;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	@Override
	public int compareTo(CandleTick o) {
		return volume.compareTo(o.getVolume());
	}

	public boolean filterByPrice() {
		final Float high = new Float(getHigh());
		final Float low = new Float(getLow());
		 Float result = (high + low) / 2;
		return result.equals(new Float(close));
	}
}
