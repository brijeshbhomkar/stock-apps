package com.data.service.dataservice.external;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.data.service.dataservice.entity.CandleTick;
import com.data.service.dataservice.json.Candles;
import com.data.service.dataservice.json.Ohlc;
import com.data.service.dataservice.pojo.DataSearchCriteria;
import com.data.service.dataservice.response.CandleResponse;
import com.data.service.dataservice.util.RestfulSupport;
import com.data.service.dataservice.util.DateUtil;

@Service
public class KiteDataService extends RestfulSupport {

	private static final Logger logger = LoggerFactory.getLogger(KiteDataService.class);

	public CandleResponse get(final DataSearchCriteria dataSearchCriteria, final Long id) {
		logger.debug("Find the data for the symbol ");
		CandleResponse candleResponse = null;
		try {
			HttpEntity<String> entity = new HttpEntity<String>("parameters", httpHeaders);
			final String url = generateKiteEndpont(Long.toString(id), dataSearchCriteria.getPeriod(),
					dataSearchCriteria.getKiteId(), dataSearchCriteria.getStartDate(), dataSearchCriteria.getEndDate());
			ResponseEntity<CandleResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity,
					CandleResponse.class);
			candleResponse = response.getBody();
		} catch (HttpClientErrorException e) {
			logger.error("Failed to find data for symbol ", e);
			throw new RuntimeException("Failed to find data for symbol ", e);
		}
		return candleResponse;
	}

	public List<CandleTick> extractData(final Candles candles, final String symbolName, final String period) {
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
				tick.setTickTime(DateUtil.convertToDate(ohlc.getTime()));
				tick.setVolume(ohlc.getVolume());
				tick.setPeriod(period);
				ticks.add(tick);
			}
		}

		return ticks;
	}

}
