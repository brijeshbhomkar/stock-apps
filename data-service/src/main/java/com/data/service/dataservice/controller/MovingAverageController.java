package com.data.service.dataservice.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.data.service.dataservice.entity.CandleTick;
import com.data.service.dataservice.entity.Symbol;
import com.data.service.dataservice.external.KiteDataService;
import com.data.service.dataservice.json.Candles;
import com.data.service.dataservice.json.Ohlc;
import com.data.service.dataservice.pojo.DataSearchCriteria;
import com.data.service.dataservice.repository.SymbolRepository;
import com.data.service.dataservice.response.CandleResponse;
import com.data.service.dataservice.technicalanalysis.MovingAverage;

@RestController
@RequestMapping("/api/ma")
public class MovingAverageController {

	private static final Logger log = LoggerFactory.getLogger(MovingAverageController.class);

	@Autowired
	private KiteDataService kiteDataService;

	@Autowired
	private SymbolRepository symbolRepository;

	@GetMapping("/{symbol}/{ma}")
	public ResponseEntity<?> movingAverage(@PathVariable final String symbol, @PathVariable final String ma) {
		// final List<String> stocks = new ArrayList<String>();
		boolean isAboveMovingAverage = false;
		final Symbol sym = symbolRepository.findSymbol(symbol);
		final DataSearchCriteria dataSearchCriteria = new DataSearchCriteria();
		dataSearchCriteria.setKiteId("RB1822");
		dataSearchCriteria.setSymbol(symbol);
		dataSearchCriteria.setStartDate(LocalDate.now().minusDays(Long.parseLong(ma)).toString());
		dataSearchCriteria.setEndDate(LocalDate.now().toString());
		dataSearchCriteria.setPeriod("day");

		CandleResponse data = kiteDataService.get(dataSearchCriteria, sym.getSymbolId());
		final List<CandleTick> candleTicks = extractData(data.getData(), sym.getSymbolId(), sym.getSymbol(),
				dataSearchCriteria.getPeriod());
		final MovingAverage movingAverage = new MovingAverage(candleTicks.size());
		final List<Double> result = new ArrayList<Double>(1);
		candleTicks.forEach(o -> {
			result.add(movingAverage.next(o.getClose()));
		});
//		if (!CollectionUtils.isEmpty(result)) {
//			if (result.get(0).doubleValue() > 50) {
//				isAboveMovingAverage = true;
//			}
//		}
		return new ResponseEntity<List<Double>>(result, HttpStatus.OK);
	}

	private List<CandleTick> extractData(final Candles candles, final Long id, final String symbolName,
										 final String period) {
		final List<CandleTick> ticks = new ArrayList<>();
		if (candles != null && candles.getCandles() != null) {
			final List<Ohlc> ohlcData = candles.getCandles();
			for (Ohlc ohlc : ohlcData) {
				final CandleTick tick = new CandleTick();
				tick.setSymbol(symbolName);
				tick.setOpen(ohlc.getOpen());
				tick.setHigh(ohlc.getHigh());
				tick.setLow(ohlc.getLow());
				tick.setClose(ohlc.getClose());
				tick.setTickTime(convertToDate(ohlc.getTime()));
				tick.setVolume(ohlc.getVolume());
				tick.setPeriod(period);
				ticks.add(tick);
			}
		}

		return ticks;
	}

	private static Date convertToDate(final String date) {
		LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ"));
		return java.sql.Timestamp.valueOf(localDateTime);
	}
}
