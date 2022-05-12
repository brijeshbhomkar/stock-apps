package com.vwap.trading.steps;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.vwap.trading.jsons.Candles;
import com.vwap.trading.models.Vwap;
import com.vwap.trading.repositories.VwapRepository;

@Component
public class VwapItemProcessor implements ItemProcessor<List<VwapReader>, Vwap> {

	@Autowired
	private VwapRepository vwapRepository;

	@Override
	public Vwap process(final List<VwapReader> readers) throws Exception {
		if (!CollectionUtils.isEmpty(readers)) {
			readers.forEach(r -> {
				calculateVWap(r.getCandles(), r.getSymbolId(), r.getSymbolName());
			});
		}
		return null;
	}

	private void calculateVWap(final Candles candles, final String symbolId, final String symbolName) {
		candles.getCandles().forEach(c -> {
			final Vwap vwapObj = new Vwap();

			final double high = c.getHigh();
			final double low = c.getLow();
			final double close = c.getClose();
			final double volume = c.getVolume();
			final double typicalPrice = (high + low + close) / 3;
			final double totalTypical = typicalPrice * volume;
			final double vwap = totalTypical / volume;

			vwapObj.setSymbolId(symbolId);
			vwapObj.setSymbolName(symbolName);
			vwapObj.setHigh(high);
			vwapObj.setLow(low);
			vwapObj.setClose(close);
			vwapObj.setVolume(volume);
			
			final BigDecimal totalPrc = new BigDecimal(typicalPrice).setScale(2, RoundingMode.HALF_UP);
			vwapObj.setTypicalValue(Double.parseDouble(totalPrc.toString()));
			final BigDecimal totalVp = new BigDecimal(totalTypical).setScale(2, RoundingMode.HALF_UP);
			vwapObj.setTotalVP(Double.parseDouble(totalVp.toString()));
			final BigDecimal totalVwap = new BigDecimal(vwap).setScale(2, RoundingMode.HALF_UP);
			vwapObj.setVwap(Double.parseDouble(totalVwap.toString()));
			LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(c.getDate()), ZoneId.systemDefault());
			vwapObj.setTimeStamp(date.toString());
			vwapRepository.delete(vwapObj);
			vwapRepository.save(vwapObj);
		});

	}

}
