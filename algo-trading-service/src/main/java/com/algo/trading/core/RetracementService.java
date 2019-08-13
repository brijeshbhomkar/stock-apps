package com.algo.trading.core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

	@Scheduled(cron = "0 0/10 * * * 1-5")
	public void calculateRetracement() {
		System.out.println(" Calculating retracment levels ");
		final List<DataRequest> requests = new ArrayList<>();
		final List<Symbol> symbols = symbolRepository.findAll();
		if (!CollectionUtils.isEmpty(symbols)) {
			symbols.forEach(s -> {
				final DataRequest dataRequest = new DataRequest();
				dataRequest.setSymbolName(s.getSymbol());
				dataRequest.setSymbol(Long.toString(s.getSymbolId()));
				dataRequest.setTimeframe("day");
				dataRequest.setUserId("RB1822");
				dataRequest.setFromDate(LocalDate.now().toString());
				dataRequest.setToDate(LocalDate.now().toString());
				requests.add(dataRequest);
			});
		}
		
		cleanup();
		process(requests);
	}

	public void process(final List<DataRequest> requests) {
		if (requests != null) {
			requests.forEach(re -> {
				Candle candle = null;
				final CandleResponse response = dataService.dataExchange(re);
				if (response != null && response.getData() != null
						&& !CollectionUtils.isEmpty(response.getData().getCandles())) {
					List<Candle> candles = response.getData().getCandles();
					candle = candles.get(0);
				}

				// double open = candle.getOpen();
				// double close = candle.getClose();
				double diff = candle.getHigh() - candle.getLow();

				Double retrace38 = null;
				Double retrace50 = null;
				Double retrace61 = null;

				if (diff > 20) {
					retrace38 = calculateDownRetracment(diff, candle.getHigh(), 38.2);
					retrace50 = calculateDownRetracment(diff, candle.getHigh(), 50);
					retrace61 = calculateDownRetracment(diff, candle.getHigh(), 61.8);
				} else {
					retrace38 = calculateUpRetracment(diff, candle.getLow(), 38.2);
					retrace50 = calculateUpRetracment(diff, candle.getLow(), 50);
					retrace61 = calculateUpRetracment(diff, candle.getLow(), 61.8);
				}

				final Retracement retracment = new Retracement();
				retracment.setSymbolName(re.getSymbolName());
				retracment.setSymbol(Long.parseLong(re.getSymbol()));
				retracment.setDailyLevel38(Double.toString(retrace38));
				retracment.setDailyLevel50(Double.toString(retrace50));
				retracment.setDailyLevel61(Double.toString(retrace61));
				retracementRepository.save(retracment);

			});
		}
	}

	public void cleanup() {
		retracementRepository.deleteAll();
	}

	/**
	 * @param diff
	 * @param open
	 * @param percentage
	 * @return
	 */
	private double calculateDownRetracment(final double diff, final double open, final double percentage) {
		return (open - (diff * percentage) / 100);
	}

	/**
	 * @param diff
	 * @param open
	 * @param percentage
	 * @return
	 */
	private double calculateUpRetracment(final double diff, final double open, final double percentage) {
		return (open + (diff * percentage) / 100);
	}
}
