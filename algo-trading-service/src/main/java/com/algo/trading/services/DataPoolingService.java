package com.algo.trading.services;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.algo.trading.entities.OrderResult;
import com.algo.trading.entities.StockRequest;
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

		private AtomicReference<StockRequest> stockRequest;

		public void setDataRequest(StockRequest dataRequest) {
			this.stockRequest = new AtomicReference<StockRequest>(dataRequest);
		}

		@Override
		public void run() {
			final CandleResponse response = dataFetchService.dataExchange(stockRequest.get().getDataRequest());
			if (response != null && !CollectionUtils.isEmpty(response.getData().getCandles())) {
				response.getData().getCandles().forEach(s -> {
					final StockRequest stckRequest = stockRequest.get();
					final DataRequest dataRequest = stockRequest.get().getDataRequest();
					System.out.println(" candle " + s.toString() + " trigger price : " + stckRequest.getTriggerPrice());
					if (s.getClose() == stckRequest.getTriggerPrice()) { // calculate loss
						final OrderResult orderResult = new OrderResult();
						orderResult.setSymbolId(dataRequest.getSymbol());
						orderResult.setSymbolName(dataRequest.getSymbolName());
						final double loss = stckRequest.getTotalPrice()
								- (stckRequest.getTriggerPrice() * stckRequest.getQuantity());
						orderResult.setLoss(loss);
						orderResultRepository.save(orderResult);
					} else {
						if (s.getOpen() <= stckRequest.getPer30profit()
								&& s.getOpen() > stckRequest.getPer20Profit()) {
							final OrderResult orderResult = new OrderResult();
							orderResult.setSymbolId(dataRequest.getSymbol());
							orderResult.setSymbolName(dataRequest.getSymbolName());
							final double profit = (s.getOpen() * stckRequest.getQuantity())
									- stckRequest.getTotalPrice();
							orderResult.setProfit(profit);
							orderResultRepository.save(orderResult);
						}
					}
				});
			}
		}

	}
}
