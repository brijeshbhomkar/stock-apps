package com.vwap.trading.steps;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.vwap.trading.jsons.GainerLoserResponse;
import com.vwap.trading.models.GainerLoser;
import com.vwap.trading.services.LoserGainerService;

@Component
public class StockItemReader implements ItemReader<StockReader> {
	
	@Autowired
	private LoserGainerService loserGainerService;
	
	//gainers
	final String NSE_NIFTY_TOP_GAINER_1 = "https://www.nseindia.com/live_market/dynaContent/live_analysis/gainers/niftyGainers1.json";
	final String NSE_NIFTY_TOP_GAINER_2 = "https://www.nseindia.com/live_market/dynaContent/live_analysis/gainers/jrNiftyGainers1.json";
	
	//loosers
	final String NSE_NIFTY_LOSER_1 = "https://www.nseindia.com/live_market/dynaContent/live_analysis/losers/niftyLosers1.json";
	final String NSE_NIFTY_LOSER_2 = "https://www.nseindia.com/live_market/dynaContent/live_analysis/losers/jrNiftyLosers1.json";
	
	@Override
	public StockReader read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		final GainerLoserResponse topGainers = loserGainerService.getGainers(NSE_NIFTY_TOP_GAINER_1);
		final Map<String, List<GainerLoser>> maps = new ConcurrentHashMap<>();
		if (topGainers != null && !CollectionUtils.isEmpty(topGainers.getData())) {
			final List<GainerLoser> filtered = topGainers.getData().stream()
					.sorted(Comparator.comparing(GainerLoser::getPerChange).reversed()).limit(3)
					.collect(Collectors.toList());
			maps.put("GAINER", filtered);
		}
		
		final GainerLoserResponse topLosers = loserGainerService.getGainers(NSE_NIFTY_LOSER_1);
		if (topGainers != null && !CollectionUtils.isEmpty(topGainers.getData())) {
			final List<GainerLoser> filtered = topLosers.getData().stream()
					.sorted(Comparator.comparing(GainerLoser::getPerChange).reversed()).limit(3)
					.collect(Collectors.toList());
			maps.put("LOSER", filtered);
		}
		final StockReader reader = new StockReader();
		reader.setMaps(maps);
		return reader;
	}

}
