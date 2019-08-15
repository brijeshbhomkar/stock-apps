package com.algo.trading.services;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.algo.trading.entities.StockRequest;
import com.algo.trading.jsons.Candle;
import com.algo.trading.jsons.CandleResponse;

@Service
public class DataPoolingService {

	@Autowired
	private DataFetchService dataFetchService;

	private final BlockingQueue<StockRequest> requestQueue = new ArrayBlockingQueue<>(5);

	private final BlockingQueue<Candle> responseQueue = new ArrayBlockingQueue<>(5);

	final ScheduledExecutorService service = Executors.newScheduledThreadPool(5);

	public void addToQueue(final StockRequest request) {
		requestQueue.add(request);
	}

	public void start() {
		if (!requestQueue.isEmpty()) {
			final DataPoolThread t1 = new DataPoolThread(requestQueue.poll());
			service.schedule(t1, 1, TimeUnit.MINUTES);
		}
	}

	@Component
	private class DataPoolThread implements Runnable {

		private StockRequest dataRequest;

		public DataPoolThread(final StockRequest dataRequest) {
			this.dataRequest = dataRequest;
		}

		@Override
		public void run() {
			final CandleResponse response = dataFetchService.dataExchange(dataRequest.getDataRequest());
			if (response != null && !CollectionUtils.isEmpty(response.getData().getCandles())) {
				
			}
		}

	}
}
