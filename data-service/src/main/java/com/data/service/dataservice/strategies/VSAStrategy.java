package com.data.service.dataservice.strategies;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.data.service.dataservice.json.Candle;

public class VSAStrategy {

	/**
	 * Find the high volume from the give candles ticks.
	 * 
	 * @param ohlcs The list of candle ticks
	 * @param limit The limit
	 * @return The list of filtered candle ticks.
	 */
	public static List<Candle> findHighVolume(final List<Candle> ohlcs, final int limit) {
		List<Candle> result = new ArrayList<Candle>();
		result = ohlcs.stream().sorted(Comparator.comparing(Candle::getVolume).reversed()).collect(Collectors.toList());
		return result;
	}

	/**
	 * Find the same open and close price.
	 * 
	 * @param ohlcs The list of candles
	 * @return The list of filtered with same open and close price
	 */
	public static List<Candle> findSameOpenClose(final List<Candle> ohlcs) {
		List<Candle> result = new ArrayList<Candle>();
		ohlcs.forEach(o -> {
			if (o.getOpen() == o.getClose()) {
				result.add(o);
			}
		});
		return result;
	}

	/**
	 * Find the wide spread candles.
	 * 
	 * @param ohlcs  The list of candles.
	 * @param spread The spread is a difference between high and low
	 * @return The list of candles.
	 */
	public static List<Candle> findWideSpreadCandles(final List<Candle> ohlcs, final int spread) {
		List<Candle> result = new ArrayList<Candle>();
		ohlcs.forEach(o -> {
			if ((o.getHigh() - o.getLow()) > spread) {
				result.add(o);
			}
		});
		return result;
	}

}
