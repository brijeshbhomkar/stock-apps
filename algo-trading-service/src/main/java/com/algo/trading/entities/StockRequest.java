package com.algo.trading.entities;

import java.io.Serializable;

import com.algo.trading.jsons.DataRequest;

public class StockRequest implements Serializable {

	private static final long serialVersionUID = 648774180791507022L;
	
	private Double triggerPrice;
	private DataRequest dataRequest;
	
	public StockRequest(final Double triggerPrice, final DataRequest request) {
		this.triggerPrice= triggerPrice;
		this.dataRequest = request;
	}

	public DataRequest getDataRequest() {
		return dataRequest;
	}

	public void setDataRequest(DataRequest dataRequest) {
		this.dataRequest = dataRequest;
	}

	public Double getTriggerPrice() {
		return triggerPrice;
	}

	public void setTriggerPrice(Double triggerPrice) {
		this.triggerPrice = triggerPrice;
	}
}
