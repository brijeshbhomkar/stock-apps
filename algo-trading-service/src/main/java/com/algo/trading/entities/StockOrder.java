package com.algo.trading.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.algo.trading.utils.TradeStatus;
import com.algo.trading.utils.TradeType;

@Entity
@Table(name = "Stock_Order", uniqueConstraints = { @UniqueConstraint(columnNames = { "symbolName" }),
		@UniqueConstraint(columnNames = { "symbolId" }) })
public class StockOrder implements Serializable {

	private static final long serialVersionUID = -8654950769012584187L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Enumerated(EnumType.STRING)
	private TradeType tradeType;

	private String symbolId;
	private String symbolName;
	private int quantity;
	private double price;
	private double triggerPrice;
	private double stopLoss;
	
	@Enumerated(EnumType.STRING)
	private TradeStatus status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TradeType getTradeType() {
		return tradeType;
	}

	public void setTradeType(TradeType tradeType) {
		this.tradeType = tradeType;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTriggerPrice() {
		return triggerPrice;
	}

	public void setTriggerPrice(double triggerPrice) {
		this.triggerPrice = triggerPrice;
	}

	public double getStopLoss() {
		return stopLoss;
	}

	public void setStopLoss(double stopLoss) {
		this.stopLoss = stopLoss;
	}

	public TradeStatus getStatus() {
		return status;
	}

	public void setStatus(TradeStatus status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + quantity;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		temp = Double.doubleToLongBits(stopLoss);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((symbolId == null) ? 0 : symbolId.hashCode());
		result = prime * result + ((symbolName == null) ? 0 : symbolName.hashCode());
		result = prime * result + ((tradeType == null) ? 0 : tradeType.hashCode());
		temp = Double.doubleToLongBits(triggerPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		StockOrder other = (StockOrder) obj;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (quantity != other.quantity)
			return false;
		if (status != other.status)
			return false;
		if (Double.doubleToLongBits(stopLoss) != Double.doubleToLongBits(other.stopLoss))
			return false;
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
		if (tradeType != other.tradeType)
			return false;
		if (Double.doubleToLongBits(triggerPrice) != Double.doubleToLongBits(other.triggerPrice))
			return false;
		return true;
	}

}
