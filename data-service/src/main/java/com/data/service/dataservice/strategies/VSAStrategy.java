package com.data.service.dataservice.strategies;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
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
		final List<Candle> result = new CopyOnWriteArrayList<Candle>(ohlcs);
//		result.forEach(o -> {
//			if ((o.getHigh() - o.getLow()) > spread) {
//				o.setSignal(1);
//			} else {
//				o.setSignal(0);
//			}
//		});
		return result;
	}

	public static LinkedList<Candle> openCloseStrategy(final List<Candle> candleTicks) {
		final LinkedList<Candle> ticks = new LinkedList<>(candleTicks);
		int count = 0;
		Candle prev = null;
		while (ticks.size() > count) {
			if (prev == null) {
				prev = ticks.getFirst();
			} else {
				Candle curr = ticks.get(count);
				if (curr.getOpen() >= prev.getHigh() && curr.getClose() <= prev.getLow()) {
					// find candles which has prev open < next day open
					// and next day close < prev close
					prev = curr;
					int i = count;
					i++;
					Candle next = null;

					if (i < ticks.size()) {
						next = ticks.get(i);
					}

					if (next != null && next.getOpen() <= curr.getLow() && next.getOpen() >= next.getClose()) {
						Candle c = ticks.get(count);
						c.setDown(c.getDate());
					}
					// if (next != null && next.getOpen() < curr.getClose() && next.getClose() <
					// next.getOpen()) {
					// result.add(ticks.get(count));
					// Candle c = ticks.get(count);
					// c.setDown(c.getDate());
					// }
					// ticks.get(count).setSignal(curr.getDate());
				}
//				else if (curr.getClose() == prev.getClose() && curr.getClose() > curr.getOpen()) {
//					Candle c = ticks.get(count);
//					c.setUp(c.getDate());
//				}
				prev = curr;
			}
			count++;
		}
		return ticks;
	}

	public static List<Candle> highVolumeLowBody(final List<Candle> candle) {
		final List<Candle> candles = new ArrayList<Candle>(candle);
		List<Candle> volumes = candles.stream().sorted(Comparator.comparing(Candle::getVolume).reversed()).limit(10)
				.collect(Collectors.toList());
		volumes.forEach(v -> {
			for (int i = 0; i < candle.size(); i++) {
				Candle curr = candle.get(i);
				if (v.getDate() == curr.getDate()) {
					if (i <= candle.size()) {
						Candle next = candle.get(i++);
						if (next != null && next.getOpen() > next.getClose()) {
							curr.setDown(curr.getDate());
					} else {
						curr.setUp(curr.getDate());
					}
					
				}
			}
			}
		});
		return candle;
}}
