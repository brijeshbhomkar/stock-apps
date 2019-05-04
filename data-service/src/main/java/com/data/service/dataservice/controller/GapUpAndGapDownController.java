package com.data.service.dataservice.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.data.service.dataservice.entity.CandleTick;
import com.data.service.dataservice.entity.Symbol;
import com.data.service.dataservice.external.KiteDataService;
import com.data.service.dataservice.external.NSEService;
import com.data.service.dataservice.json.StockWatch;
import com.data.service.dataservice.pojo.DataSearchCriteria;
import com.data.service.dataservice.repository.SymbolRepository;
import com.data.service.dataservice.response.CandleResponse;
import com.data.service.dataservice.response.StockWatchResponse;
import com.data.service.dataservice.util.EndpointUrls;

/**
 * 
 * This strategy is based on the stock gap up and gap down.
 * 
 * @author P10458159
 *
 */
@RestController
@RequestMapping("/api/nse/gapup")
public class GapUpAndGapDownController {

	@Autowired
	private SymbolRepository symbolRepository;

	@Autowired
	private KiteDataService kiteDataService;

	@Autowired
	private NSEService nseService;

	@RequestMapping("/{symbol}/{days}")
	public ResponseEntity<?> singleStock(@PathVariable final String symbol, @PathVariable final String days) {
		final Symbol sym = symbolRepository.findSymbol(symbol);
		final List<CandleTick> response = new ArrayList<CandleTick>();
		final DataSearchCriteria dataSearchCriteria = new DataSearchCriteria();
		dataSearchCriteria.setKiteId("RB1822");
		dataSearchCriteria.setSymbol(symbol);
		dataSearchCriteria.setStartDate(LocalDate.now().minusDays(Long.parseLong(days)).toString());
		dataSearchCriteria.setEndDate(LocalDate.now().toString());
		dataSearchCriteria.setPeriod("day");

		CandleResponse data = kiteDataService.get(dataSearchCriteria, sym.getSymbolId());
		final List<CandleTick> candleTicks = kiteDataService.extractData(data.getData(), sym.getSymbolId(),
				sym.getSymbol(), dataSearchCriteria.getPeriod());
		if (!CollectionUtils.isEmpty(candleTicks)) {
			boolean skip = true;
			for (int i = 0; i < candleTicks.size(); i++) {
				if (!skip) {
					CandleTick prev = candleTicks.get(i - 1);
					CandleTick next = candleTicks.get(i);
					final Float open = new Float(next.getOpen());
					final Float close = new Float(prev.getClose());
					Float result = (open - close) / close;
					result = result * 100;
					if (result > 2) {
						response.add(next);
					}
				}
				skip = false;
			}
		}
		return new ResponseEntity<List<CandleTick>>(response.stream()
				.sorted(Comparator.comparing(CandleTick::getVolume).reversed()).limit(5).collect(Collectors.toList()),
				HttpStatus.OK);
	}

	@RequestMapping("/all")
	public ResponseEntity<?> findGapUpStocks() {
		return new ResponseEntity<List<StockWatch>>(findStocks(), HttpStatus.OK);
	}

	private List<StockWatch> findStocks() {
		StockWatchResponse response = null;

		List<StockWatch> stocks = new ArrayList<>();
		List<StockWatch> data = new ArrayList<StockWatch>();

		response = nseService.fetchNseStockWatch(EndpointUrls.NSE_NIFTY_STOCKWATCH_50);
		if (!CollectionUtils.isEmpty(response.getData())) {
			stocks.addAll(response.getData().stream().collect(Collectors.toList()));
		}

		response = nseService.fetchNseStockWatch(EndpointUrls.NSE_NIFTY_STOCKWATCH_NEXT_50);
		if (!CollectionUtils.isEmpty(response.getData())) {
			stocks.addAll(response.getData().stream().collect(Collectors.toList()));
		}

		response = nseService.fetchNseStockWatch(EndpointUrls.NSE_NIFTY_STOCKWATCH_MIDCAP_50);
		if (!CollectionUtils.isEmpty(response.getData())) {
			stocks.addAll(response.getData().stream().collect(Collectors.toList()));
		}

		response = nseService.fetchNseStockWatch(EndpointUrls.NSE_NIFTY_STOCKWATCH_MIDCAP_150);
		if (!CollectionUtils.isEmpty(response.getData())) {
			stocks.addAll(response.getData().stream().collect(Collectors.toList()));
		}

		response = nseService.fetchNseStockWatch(EndpointUrls.NSE_NIFTY_STOCKWATCH_SMALCAP_50);
		if (!CollectionUtils.isEmpty(response.getData())) {
			stocks.addAll(response.getData().stream().collect(Collectors.toList()));
		}

		response = nseService.fetchNseStockWatch(EndpointUrls.NSE_NIFTY_STOCKWATCH_SMALCAP_250);
		if (!CollectionUtils.isEmpty(response.getData())) {
			stocks.addAll(response.getData().stream().collect(Collectors.toList()));
		}

		response = nseService.fetchNseStockWatch(EndpointUrls.NSE_NIFTY_STOCKWATCH_MIDCAP_400);
		if (!CollectionUtils.isEmpty(response.getData())) {
			stocks.addAll(response.getData().stream().collect(Collectors.toList()));
		}

		if (!CollectionUtils.isEmpty(stocks)) {
			stocks.forEach(s -> {
				Float open = new Float(s.getOpen());
				Float prevClose = new Float(s.getPreviousClose());
				Float result = (open - prevClose) / prevClose;
				result = result * 100;
				if (result >= 2) {
					data.add(s);
				}
			});
		}

		return data;
	}
}
