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
	
	private double triggerPrice;

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

	@Override
	public String toString() {
		return "StockOrder [id=" + id + ", symbolId=" + symbolId + ", symbolName=" + symbolName + ", triggerPrice="
				+ triggerPrice + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((symbolId == null) ? 0 : symbolId.hashCode());
		result = prime * result + ((symbolName == null) ? 0 : symbolName.hashCode());
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
		OrderJob other = (OrderJob) obj;
		if (symbolId == null) {
			if (other.symbolId != null)
				return false;
		} else if (!symbolId.equals(other.symbolId))
			return false;
		if (symbolName == null) {
			if (other.symbolName != null)
				return false;
		} else if (!symbolName.equals(other.symbolName))
			return false;
		return true;
	}

	public double getTriggerPrice() {
		return triggerPrice;
	}

	public void setTriggerPrice(double triggerPrice) {
		this.triggerPrice = triggerPrice;
	}
	
	
}
