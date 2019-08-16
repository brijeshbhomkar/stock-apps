package com.algo.trading.services;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.algo.trading.entities.OrderResult;
import com.algo.trading.entities.StockRequest;
import com.algo.trading.jsons.Candle;
import com.algo.trading.jsons.CandleResponse;
import com.algo.trading.jsons.DataRequest;
import com.algo.trading.repositories.OrderResultRepository;

@Service
public class DataPoolingService {

	@Autowired
	private DataFetchService dataFetchService;
	
	@Autowired
	private OrderResultRepository orderResultRepository;

	private final BlockingQueue<StockRequest> requestQueue = new ArrayBlockingQueue<>(5);

	private final BlockingQueue<Candle> responseQueue = new ArrayBlockingQueue<>(5);

	final ScheduledExecutorService service = Executors.newScheduledThreadPool(5); 

	public void addToQueue(final StockRequest request) {
		requestQueue.add(request);
	}

	public void start() {
		if (!requestQueue.isEmpty()) {
			final DataPoolThread dataPoolThread = new DataPoolThread();
			dataPoolThread.setDataRequest(requestQueue.poll());
			service.schedule(dataPoolThread, 1, TimeUnit.MINUTES);
		}
	}

	private class DataPoolThread implements Runnable {

		private StockRequest stockRequest;

		public void setDataRequest(StockRequest dataRequest) {
			this.stockRequest = dataRequest;
		}

		@Override
		public void run() {
			final CandleResponse response = dataFetchService.dataExchange(stockRequest.getDataRequest());
			if (response != null && !CollectionUtils.isEmpty(response.getData().getCandles())) {
				response.getData().getCandles().forEach(s -> {
					final DataRequest request = stockRequest.getDataRequest();
					if (s.getOpen() == stockRequest.getTriggerPrice()) { // calculate loss
						//
						final OrderResult orderResult = new OrderResult();
						orderResult.setSymbolId(request.getSymbol());
						orderResult.setSymbolName(request.getSymbolName());
						final double loss = stockRequest.getTotalPrice()
								- (stockRequest.getTriggerPrice() * stockRequest.getQuantity());
						orderResult.setLoss(loss);
						orderResultRepository.save(orderResult);
					} else {
						if (s.getOpen() <= stockRequest.getPer30profit()
								&& s.getOpen() > stockRequest.getPer20Profit()) {
							final OrderResult orderResult = new OrderResult();
							orderResult.setSymbolId(request.getSymbol());
							orderResult.setSymbolName(request.getSymbolName());
							final double profit = (s.getOpen() * stockRequest.getQuantity())
									- stockRequest.getTotalPrice();
							orderResult.setProfit(profit);
							orderResultRepository.save(orderResult);
						}
					}
				});
			}
		}

	}

	public BlockingQueue<Candle> getResponseQueue() {
		return responseQueue;
	}
}
