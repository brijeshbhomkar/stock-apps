package com.algo.trading.services;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.algo.trading.entities.Retracement;
import com.algo.trading.entities.StockJob;
import com.algo.trading.entities.OrderJob;
import com.algo.trading.jsons.Candle;
import com.algo.trading.jsons.CandleResponse;
import com.algo.trading.jsons.DataRequest;
import com.algo.trading.repositories.RetracementRepository;
import com.algo.trading.repositories.StockJobRepository;

@Service
public class SchedulingService {

	@Autowired
	private DataFetchService dataService;

	@Autowired
	private StockJobRepository jobRepository;

	@Autowired
	private RetracementRepository retracementRepository;

	@Autowired
	private OrderPlaceService orderPlaceService;

	private BlockingQueue<StockJob> arrayBlockingQueue = new ArrayBlockingQueue<>(20);

	Retracement retracement = null;

	@Scheduled(cron = "0 0/2 * * * 1-5")
	public void start() {
		if (!arrayBlockingQueue.isEmpty()) {
			final StockJob stockJob = arrayBlockingQueue.poll();
			final DataRequest request = new DataRequest();
			request.setSymbol(stockJob.getSymbolId());
			request.setSymbolName(stockJob.getSymbol());
			request.setTimeframe("2minute");
			request.setFromDate(LocalDate.now().toString());
			request.setToDate(LocalDate.now().toString());
			final CandleResponse response = dataService.dataExchange(request); // live data
			if (retracement == null) {
				retracement = retracementRepository.findRetracementById(stockJob.getSymbolId()); // find the retracement
			}
			if (response != null) {
				final List<Candle> candles = response.getData().getCandles().stream()
						.filter(s -> s.getOpen() == Double.parseDouble(retracement.getTriggerPrice()))
						.collect(Collectors.toList());
				candles.forEach(c -> {
					final OrderJob stockOrder = new OrderJob();
					stockOrder.setSymbolId(request.getSymbol());
					stockOrder.setSymbolName(request.getSymbolName());
					orderPlaceService.saveOrder(stockOrder);
				});
			}
		}
	}

	@Scheduled(cron = "0 0/5 * * * 1-5")
	public void activeJobs() {
		System.out.println(" Running cron job to get the jobs every 10 minutes ");
		final List<StockJob> jobs = jobRepository.findAll().stream().filter(j -> j.getStatus().equals("Active"))
				.collect(Collectors.toList());
		jobs.forEach(j -> {
			arrayBlockingQueue.add(j);
		});
	}
}
