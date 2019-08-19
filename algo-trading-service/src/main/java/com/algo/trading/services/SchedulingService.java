package com.algo.trading.services;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.algo.trading.entities.OrderJob;
import com.algo.trading.entities.Retracement;
import com.algo.trading.entities.StockJob;
import com.algo.trading.entities.Symbol;
import com.algo.trading.jsons.Candle;
import com.algo.trading.jsons.CandleResponse;
import com.algo.trading.jsons.DataRequest;
import com.algo.trading.repositories.RetracementRepository;
import com.algo.trading.repositories.StockJobRepository;
import com.algo.trading.repositories.SymbolRepository;
import com.algo.trading.utils.TradeStatus;

@Service
public class SchedulingService {

	@Autowired
	private DataFetchService dataService;

	@Autowired
	private StockJobRepository stockJobRepository;

	@Autowired
	private RetracementRepository retracementRepository;

	@Autowired
	private OrderPlaceService orderPlaceService;

	@Autowired
	private SymbolRepository symbolRepository;

	private BlockingQueue<StockJob> arrayBlockingQueue = new ArrayBlockingQueue<>(50);

	Retracement retracement = null;

	@PostConstruct
	public void clean() {
		stockJobRepository.deleteAll();
	}

	@Scheduled(cron = "* 0/5 * * * 1-5")
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
			retracement = retracementRepository.findRetracementById(stockJob.getSymbolId()); // find the retracement
			if (response != null) {
				final List<Candle> candles = response.getData().getCandles().stream()
						.filter(s -> s.getOpen() == Double.parseDouble(retracement.getTriggerPrice()))
						.collect(Collectors.toList());
				candles.forEach(c -> {
					final OrderJob stockOrder = new OrderJob();
					stockOrder.setSymbolId(request.getSymbol());
					stockOrder.setSymbolName(request.getSymbolName());
					stockOrder.setTriggerPrice(c.getOpen());
					orderPlaceService.saveOrder(stockOrder);
				});
			}
		}
	}

	@Scheduled(cron = "* 0/5 * * * 1-5")
	public void activeJobs() {
		System.out.println(" Find the jobs at the start of market 9:30 am ");
		final List<StockJob> jobs = stockJobRepository.findAll().stream().filter(j -> j.getStatus().equals("Active"))
				.collect(Collectors.toList());
		jobs.forEach(j -> {
			if (!arrayBlockingQueue.contains(j)) {
				arrayBlockingQueue.add(j);
			}
		});
	}

	@Scheduled(cron = "* 0/5 * * * 1-5")
	public void startJobCreation() {
		System.out.println(" Creating jobs at the start of market 9:15 am ");
		final List<Symbol> symbols = symbolRepository.findAll();
		if (!CollectionUtils.isEmpty(symbols)) {
			symbols.forEach(s -> {
				final StockJob job = new StockJob();
				job.setSymbolId(Long.toString(s.getSymbolId()));
				job.setSymbol(s.getSymbol());
				job.setTimeframe("1minute");
				job.setStatus(TradeStatus.ACTIVE.getStatus());
				stockJobRepository.delete(job);
				stockJobRepository.save(job);
			});
		}
	}
}
