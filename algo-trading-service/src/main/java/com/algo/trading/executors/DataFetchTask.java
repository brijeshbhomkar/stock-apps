package com.algo.trading.executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algo.trading.pojos.DataRequest;
import com.algo.trading.services.DataFetchService;

@Component
public class DataFetchTask implements RunnableTask {
	
	@Autowired
	private DataFetchService dataService;
	
	@Autowired
	private DataRequest request;
	
	public DataRequest getRequest() {
		return request;
	}

	public void setRequest(DataRequest request) {
		this.request = request;
	}

	@Override
	public void run() {
		dataService.exchange(request);
	}

}
