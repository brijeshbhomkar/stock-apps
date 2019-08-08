package com.algo.trading.core;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.algo.trading.executors.TaskExecutorImpl;
import com.algo.trading.jsons.Candle;
import com.algo.trading.jsons.CandleResponse;
import com.algo.trading.jsons.DataRequest;
import com.algo.trading.services.DataFetchService;

@Service
public class FibonacciRetracementTrading {

	@Autowired
	private TaskExecutorImpl taskExecutor;

	@Autowired
	private DataFetchService dataService;

	public void process(final String id) {
		// 1. find the daily candle stick
		// 2. find open and close
		// 2. calculate different levels of fibonacci
		// 3. schedule thread to fetch 2 mins data
		// 4. validate against different levels
		// 5. if level matches, then place order
		// 6.
		final DataRequest dataRequest = new DataRequest();
		dataRequest.setSymbol("256265");
		dataRequest.setTimeframe("day");
		dataRequest.setUserId("RB1822");
		dataRequest.setFromDate(LocalDate.now().toString());
		dataRequest.setToDate(LocalDate.now().toString());

		final CandleResponse response = dataService.exchange(dataRequest);
		if (response != null && response.getData() != null
				&& !CollectionUtils.isEmpty(response.getData().getCandles())) {
			List<Candle> candles = response.getData().getCandles();

		}
	}
}
