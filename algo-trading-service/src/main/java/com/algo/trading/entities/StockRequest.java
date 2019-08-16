package com.algo.trading.entities;

import java.io.Serializable;

import com.algo.trading.jsons.DataRequest;

public class StockRequest implements Serializable {

	private static final long serialVersionUID = 648774180791507022L;

	private double per20Profit;
	private double per30profit;
	private double triggerPrice; // avoid loss
	private DataRequest dataRequest;
	private double buyingPrice;
	private double totalPrice;
	private int quantity;

	public StockRequest(final double triggerPrice, final DataRequest request) {
		this.triggerPrice = triggerPrice;
		this.dataRequest = request;
	}

	public DataRequest getDataRequest() {
		return dataRequest;
	}

	public void setDataRequest(DataRequest dataRequest) {
		this.dataRequest = dataRequest;
	}

	public double getTriggerPrice() {
		return triggerPrice;
	}

	public void setTriggerPrice(double triggerPrice) {
		this.triggerPrice = triggerPrice;
	}

	public double getPer30profit() {
		return per30profit;
	}

	public void setPer30profit(double per30profit) {
		this.per30profit = per30profit;
	}

	public double getOrderPrice() {
		return buyingPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.buyingPrice = orderPrice;
	}

	public double getPer20Profit() {
		return per20Profit;
	}

	public void setPer20Profit(double per20Profit) {
		this.per20Profit = per20Profit;
	}

	public double getBuyingPrice() {
		return buyingPrice;
	}

	public void setBuyingPrice(double buyingPrice) {
		this.buyingPrice = buyingPrice;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "StockRequest [per30profit=" + per30profit + ", triggerPrice=" + triggerPrice + ", dataRequest="
				+ dataRequest + ", orderPrice=" + buyingPrice + "]";
	}
}
