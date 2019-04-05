package com.data.service.dataservice.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.data.service.dataservice.entity.CandleTick;
import com.data.service.dataservice.entity.Symbol;
import com.data.service.dataservice.external.KiteDataService;
import com.data.service.dataservice.pojo.DataSearchCriteria;
import com.data.service.dataservice.repository.KiteDataRepository;
import com.data.service.dataservice.repository.SymbolRepository;
import com.data.service.dataservice.response.CandleResponse;
import com.data.service.dataservice.response.Candles;
import com.data.service.dataservice.response.Ohlc;

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
	public ResponseEntity<?> get(@RequestBody final DataSearchCriteria dataSearchCriteria) {
		logger.debug("Find the data for the given symbol ");
		try {
			final Symbol symbol = symbolRepository.findSymbolById(dataSearchCriteria.getSymbol());
			CandleResponse data = kiteDataService.get(dataSearchCriteria, symbol.getSymbolId());
			if (data == null) {
				return ResponseEntity.badRequest().body(HttpStatus.NO_CONTENT);
			}

			// insert into db
			List<CandleTick> candletTicks = extractData(data.getData(), symbol.getSymbolId(), symbol.getSymbol(),
					dataSearchCriteria.getPeriod());
			if (candletTicks != null && !candletTicks.isEmpty()) {
				for (CandleTick candleTick : candletTicks) {
					kiteDataRepository.saveAndFlush(candleTick);
				}
			}
		} catch (Exception e) {
			logger.error("Failed to find data", e);
			return ResponseEntity.badRequest().body("Failed to find data ");
		}

		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(@PathVariable final String symbol) {
		logger.debug("Delete the data for symbol : {} ", symbol);
		try {
			kiteDataRepository.deleteBySymbolId(symbol);
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
