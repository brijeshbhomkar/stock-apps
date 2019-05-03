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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.data.service.dataservice.entity.CandleTick;
import com.data.service.dataservice.entity.Symbol;
import com.data.service.dataservice.external.KiteDataService;
import com.data.service.dataservice.json.Candles;
import com.data.service.dataservice.json.Ohlc;
import com.data.service.dataservice.pojo.DataSearchCriteria;
import com.data.service.dataservice.repository.KiteDataRepository;
import com.data.service.dataservice.repository.SymbolRepository;
import com.data.service.dataservice.response.CandleResponse;

@RestController
@RequestMapping("/api/kite")
public class KiteDataController {

	private static final Logger logger = LoggerFactory.getLogger(KiteDataController.class);

	@Autowired
	private KiteDataService kiteDataService;

	@Autowired
	private KiteDataRepository kiteDataRepository;

	@Autowired
	private SymbolRepository symbolRepository;

	@PostMapping
	public ResponseEntity<List<CandleTick>> get(@RequestBody final DataSearchCriteria dataSearchCriteria) {
		logger.debug("Find the data for the given symbol ");
		List<CandleTick> candleTicks = new ArrayList<CandleTick>();
		try {
			final Symbol symbol = symbolRepository.findSymbol(dataSearchCriteria.getSymbol());
			CandleResponse data = kiteDataService.get(dataSearchCriteria, symbol.getSymbolId());
			if (data == null) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			// insert into db
			candleTicks = extractData(data.getData(), symbol.getSymbolId(), symbol.getSymbol(),
					dataSearchCriteria.getPeriod());
			if (candleTicks != null && !candleTicks.isEmpty()) {
				for (CandleTick candleTick : candleTicks) {
					kiteDataRepository.save(candleTick);
				}
			}
		} catch (Exception e) {
			logger.error("Failed to find data", e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<List<CandleTick>>(
				candleTicks.stream().filter(s -> s.getOpen().equals(s.getLow()))
						.sorted(Comparator.comparing(CandleTick::getVolume).reversed()).collect(Collectors.toList()),
				HttpStatus.OK);
	}

	/**
	 * @return The list of stocks which has same open and low, basically those which
	 *         are in uptrend with a period of a day.
	 */
	@GetMapping(path = "/upstocks")
	public ResponseEntity<?> findUpstocks() {
		final List<CandleTick> result = new ArrayList<CandleTick>();
		final List<Symbol> symbols = symbolRepository.findAll();
		if (!CollectionUtils.isEmpty(symbols)) {
			final DataSearchCriteria dataSearchCriteria = new DataSearchCriteria();
			dataSearchCriteria.setKiteId("RB1822");
			dataSearchCriteria.setStartDate(LocalDate.now().minusDays(1).toString());
			dataSearchCriteria.setEndDate(LocalDate.now().toString());
			dataSearchCriteria.setPeriod("day");
			symbols.forEach(s -> {
				dataSearchCriteria.setSymbol(s.getSymbol());
				CandleResponse data = kiteDataService.get(dataSearchCriteria, s.getSymbolId());
				final List<CandleTick> candleTicks = extractData(data.getData(), s.getSymbolId(), s.getSymbol(),
						dataSearchCriteria.getPeriod());
				final List<CandleTick> output = candleTicks.stream().filter(i -> i.getOpen().equals(i.getLow()))
						.sorted(Comparator.comparing(CandleTick::getVolume).reversed()).collect(Collectors.toList());
				result.addAll(output);
			});
		}

		if (CollectionUtils.isEmpty(result)) {
			return ResponseEntity.ok(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<CandleTick>>(result, HttpStatus.OK);
	}
	
	/**
	 * @return The list of stocks which has same open and low, basically those which
	 *         are in uptrend with a period of a day.
	 */
	@GetMapping(path = "/downstocks")
	public ResponseEntity<?> findDownStocks() {
		final List<CandleTick> result = new ArrayList<CandleTick>();
		final List<Symbol> symbols = symbolRepository.findAll();
		if (!CollectionUtils.isEmpty(symbols)) {
			final DataSearchCriteria dataSearchCriteria = new DataSearchCriteria();
			dataSearchCriteria.setKiteId("RB1822");
			dataSearchCriteria.setStartDate(LocalDate.now().minusDays(1).toString());
			dataSearchCriteria.setEndDate(LocalDate.now().toString());
			dataSearchCriteria.setPeriod("day");
			symbols.forEach(s -> {
				dataSearchCriteria.setSymbol(s.getSymbol());
				CandleResponse data = kiteDataService.get(dataSearchCriteria, s.getSymbolId());
				final List<CandleTick> candleTicks = extractData(data.getData(), s.getSymbolId(), s.getSymbol(),
						dataSearchCriteria.getPeriod());
				final List<CandleTick> output = candleTicks.stream().filter(i -> i.getOpen().equals(i.getHigh()))
						.sorted(Comparator.comparing(CandleTick::getVolume).reversed()).collect(Collectors.toList());
				result.addAll(output);
			});
		}

		if (CollectionUtils.isEmpty(result)) {
			return ResponseEntity.ok(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<CandleTick>>(result, HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<?> delete(@PathVariable final String symbol) {
		logger.debug("Delete the data for symbol : {} ", symbol);
		try {
			// kiteDataRepository.deleteBySymbolId(symbol);
		} catch (Exception e) {
			logger.error("Failed to delete");
			return ResponseEntity.badRequest().body("Failed to find symbol ");
		}
		return ResponseEntity.ok(HttpStatus.OK);
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
