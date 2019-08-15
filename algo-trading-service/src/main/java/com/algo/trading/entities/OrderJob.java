package com.algo.trading.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Order_Job",  uniqueConstraints = { @UniqueConstraint(columnNames = { "symbolId" }),
		@UniqueConstraint(columnNames = { "symbolName" }) })
public class OrderJob implements Serializable {

	private static final long serialVersionUID = 199828884248354600L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String symbolId;
	
	private String symbolName;
	
	private String triggerPrice;

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

	public String getSymbolName() {
		return symbolName;
	}

	public void setSymbolName(String symbolName) {
		this.symbolName = symbolName;
	}

	public String getTriggerPrice() {
		return triggerPrice;
	}

	public void setTriggerPrice(String triggerPrice) {
		this.triggerPrice = triggerPrice;
	}

	@Override
	public String toString() {
		return "StockOrder [id=" + id + ", symbolId=" + symbolId + ", symbolName=" + symbolName + ", triggerPrice="
				+ triggerPrice + "]";
	}
}
