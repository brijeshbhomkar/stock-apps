package com.algo.trading.core;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.algo.trading.entities.Retracement;
import com.algo.trading.entities.Symbol;
import com.algo.trading.jsons.Candle;
import com.algo.trading.jsons.CandleResponse;
import com.algo.trading.jsons.DataRequest;
import com.algo.trading.repositories.RetracementRepository;
import com.algo.trading.repositories.SymbolRepository;
import com.algo.trading.services.DataFetchService;

@Component
public class RetracementService {

	@Autowired
	private RetracementRepository retracementRepository;

	@Autowired
	private DataFetchService dataService;

	@Autowired
	private SymbolRepository symbolRepository;

	List<Symbol> symbols = null;

	@Scheduled(cron = "* 39 9 * * 1-5")
	public void scheduleRetracementCalculation() {
		System.out.println(" Calculate fibonacci retracment of all the available symbols after market 4 ");
		if (symbols == null) {
			symbols = symbolRepository.findAll();
			if (!CollectionUtils.isEmpty(symbols)) {
				retracementCalculation("day", LocalDate.now().toString());
			}
		}
	}

	public void retracementCalculation(final String timeframe, final String date) {
		retracementRepository.deleteAll();
		if (symbols == null) {
			symbols = symbolRepository.findAll();
			symbols.forEach(s -> {
				final DataRequest dataRequest = new DataRequest();
				dataRequest.setSymbolName(s.getSymbol());
				dataRequest.setSymbol(Long.toString(s.getSymbolId()));
				dataRequest.setTimeframe(timeframe);
				dataRequest.setUserId("RB1822");
				dataRequest.setFromDate(date);
				dataRequest.setToDate(date);
				try {
					final CandleResponse response = dataService.dataExchange(dataRequest);
					if (response != null && response.getData() != null
							&& !CollectionUtils.isEmpty(response.getData().getCandles())) {
						final Candle candle = response.getData().getCandles().get(0);
						calculate(candle, Long.toString(s.getSymbolId()), s.getSymbol());
					}
				} catch (Exception e) {
					System.out.println("Failed to get data " + e.getCause());
				}
			});
		}
	}

	private void calculate(final Candle candle, final String symbolId, final String symbolName) {
		double diff = candle.getHigh() - candle.getLow();

		Double retrace38 = null;
		Double retrace50 = null;
		Double retrace61 = null;

		retrace38 = calculateRetracment(diff, candle.getHigh(), 38.2);
		retrace50 = calculateRetracment(diff, candle.getHigh(), 50);
		retrace61 = calculateRetracment(diff, candle.getHigh(), 61.8);

		final Retracement retracment = new Retracement();
		retracment.setSymbolName(symbolName);
		retracment.setSymbol(Long.parseLong(symbolId));
		retracment.setDailyLevel38(Double.toString(retrace38));
		retracment.setDailyLevel50(Double.toString(retrace50));
		retracment.setDailyLevel61(Double.toString(retrace61));

		final double triggerPrice = Math.round((retrace50 + retrace61) / 2);
		retracment.setTriggerPrice(Double.toString(triggerPrice));

		retracementRepository.save(retracment);
	}

	@PostConstruct
	public void cleanup() {
		retracementRepository.deleteAll();
	}

	/**
	 * @param diff
	 * @param open
	 * @param percentage
	 * @return
	 */
	private double calculateRetracment(final double diff, final double open, final double percentage) {
		return Math.round((open - (diff * percentage) / 100));
	}
}
