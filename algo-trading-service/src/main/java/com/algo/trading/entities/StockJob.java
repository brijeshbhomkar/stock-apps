package com.algo.trading.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "STOCK_JOB", uniqueConstraints = { @UniqueConstraint(columnNames = { "symbol" }),
		@UniqueConstraint(columnNames = { "symbolId" }) })
public class StockJob implements Serializable {

	private static final long serialVersionUID = 1648765079350262514L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String symbolId;
	private String symbol;
	private String timeframe;
	private String status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSymbolId() {
		return symbolId;
	}

	public void setSymbolId(String symbolId) {
		this.symbolId = symbolId;
	}

	public String getTimeframe() {
		return timeframe;
	}

	public void setTimeframe(String timeframe) {
		this.timeframe = timeframe;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return "StockJob [id=" + id + ", symbolId=" + symbolId + ", symbol=" + symbol + ", timeframe=" + timeframe
				+ ", status=" + status + "]";
	}

}
