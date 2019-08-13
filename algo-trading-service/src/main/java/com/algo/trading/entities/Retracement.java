package com.algo.trading.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "RETRACEMENT", uniqueConstraints = { @UniqueConstraint(columnNames = { "symbol" }),
		@UniqueConstraint(columnNames = { "symbolName" }) })
public class Retracement implements Serializable {

	private static final long serialVersionUID = 2678229298857762720L;

	@Id
	private long symbol;
	private String symbolName;
	private String dailyLevel38;
	private String dailyLevel50;
	private String dailyLevel61;
	private String weeklyLevel38;
	private String weeklyLevel50;
	private String weeklyLevel61;

	public long getSymbol() {
		return symbol;
	}

	public void setSymbol(long symbol) {
		this.symbol = symbol;
	}

	public String getDailyLevel38() {
		return dailyLevel38;
	}

	public void setDailyLevel38(String dailyLevel38) {
		this.dailyLevel38 = dailyLevel38;
	}

	public String getDailyLevel50() {
		return dailyLevel50;
	}

	public void setDailyLevel50(String dailyLevel50) {
		this.dailyLevel50 = dailyLevel50;
	}

	public String getDailyLevel61() {
		return dailyLevel61;
	}

	public void setDailyLevel61(String dailyLevel61) {
		this.dailyLevel61 = dailyLevel61;
	}

	public String getWeeklyLevel38() {
		return weeklyLevel38;
	}

	public void setWeeklyLevel38(String weeklyLevel38) {
		this.weeklyLevel38 = weeklyLevel38;
	}

	public String getWeeklyLevel50() {
		return weeklyLevel50;
	}

	public void setWeeklyLevel50(String weeklyLevel50) {
		this.weeklyLevel50 = weeklyLevel50;
	}

	public String getWeeklyLevel61() {
		return weeklyLevel61;
	}

	public void setWeeklyLevel61(String weeklyLevel61) {
		this.weeklyLevel61 = weeklyLevel61;
	}

	public String getSymbolName() {
		return symbolName;
	}

	public void setSymbolName(String symbolName) {
		this.symbolName = symbolName;
	}

	@Override
	public String toString() {
		return "Retracement [symbol=" + symbol + ", symbolName=" + symbolName + ", dailyLevel38=" + dailyLevel38
				+ ", dailyLevel50=" + dailyLevel50 + ", dailyLevel61=" + dailyLevel61 + ", weeklyLevel38="
				+ weeklyLevel38 + ", weeklyLevel50=" + weeklyLevel50 + ", weeklyLevel61=" + weeklyLevel61 + "]";
	}

}
