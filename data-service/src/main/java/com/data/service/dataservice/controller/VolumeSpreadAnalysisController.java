package com.data.service.dataservice.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/api/nse/vsa")
public class VolumeSpreadAnalysisController {

	@Autowired
	private SymbolRepository symbolRepository;

	@Autowired
	private KiteDataService kiteDataService;

	@Autowired
	private NSEService nseService;

	@RequestMapping("/all/uptrend")
	public ResponseEntity<?> findUptrend() {
		// final List<StockWatch> result = new ArrayList<StockWatch>();
//		final List<Symbol> symbols = symbolRepository.findAll();
//		if (!CollectionUtils.isEmpty(symbols)) {
//			final DataSearchCriteria dataSearchCriteria = new DataSearchCriteria();
//			dataSearchCriteria.setKiteId("RB1822");
//			dataSearchCriteria.setPeriod("day");
//			dataSearchCriteria.setStartDate(LocalDate.now().minusDays(5).toString());
//			dataSearchCriteria.setEndDate(LocalDate.now().toString());
//			symbols.forEach(s -> {
//				dataSearchCriteria.setSymbol(s.getSymbol());
//				CandleResponse data = kiteDataService.get(dataSearchCriteria, s.getSymbolId());
//				final List<CandleTick> candleTicks = kiteDataService.extractData(data.getData(), s.getSymbolId(),
//						s.getSymbol(), dataSearchCriteria.getPeriod());
//				final List<CandleTick> output = candleTicks.stream().filter(CandleTick::filterByPrice)
//						.sorted(Comparator.comparing(CandleTick::getVolume).reversed()).collect(Collectors.toList());
//				result.addAll(output);
//			});
//		}
//		if (CollectionUtils.isEmpty(result)) {
//			return ResponseEntity.ok(HttpStatus.NO_CONTENT);
//		}
		final List<StockWatch> output = nse(true).stream().filter(StockWatch::filterByPrice)
				.sorted(Comparator.comparing(StockWatch::getTrdVol).reversed()).collect(Collectors.toList());
		return new ResponseEntity<List<StockWatch>>(output, HttpStatus.OK);
	}

	@RequestMapping("/volumescan/{symbol}")
	public ResponseEntity<?> scanVolume(@PathVariable final String symbol) {
		final Symbol sym = symbolRepository.findSymbol(symbol);
		final DataSearchCriteria dataSearchCriteria = new DataSearchCriteria();
		dataSearchCriteria.setKiteId("RB1822");
		dataSearchCriteria.setPeriod("day");
		dataSearchCriteria.setStartDate(LocalDate.now().minusDays(120).toString());
		dataSearchCriteria.setEndDate(LocalDate.now().toString());
		dataSearchCriteria.setSymbol(sym.getSymbol());
		CandleResponse data = kiteDataService.get(dataSearchCriteria, sym.getSymbolId());
		final List<CandleTick> candleTicks = kiteDataService.extractData(data.getData(), sym.getSymbol(),
				dataSearchCriteria.getPeriod());

		// step 1:
		// Find list of stocks with ultra high volume from last 6 months
		final List<CandleTick> result = candleTicks.stream().sorted(Comparator.comparing(CandleTick::getVolume)).limit(1).collect(Collectors.toList());
		return new ResponseEntity<List<CandleTick>>(result, HttpStatus.OK);

	}

	private List<CandleTick> findUltraHighVolume(final List<CandleTick> ticks) {
		return ticks;
	}

	@RequestMapping("/downtrend")
	public ResponseEntity<?> scan() {
		final List<CandleTick> result = new CopyOnWriteArrayList<CandleTick>();
		final List<Symbol> symbols = symbolRepository.findAll();
		if (!CollectionUtils.isEmpty(symbols)) {
			final DataSearchCriteria dataSearchCriteria = new DataSearchCriteria();
			dataSearchCriteria.setKiteId("RB1822");
			dataSearchCriteria.setPeriod("day");
			dataSearchCriteria.setStartDate(LocalDate.now().minusDays(3).toString());
			dataSearchCriteria.setEndDate(LocalDate.now().toString());
			symbols.parallelStream().forEach(s -> {
				dataSearchCriteria.setSymbol(s.getSymbol());
				CandleResponse data = kiteDataService.get(dataSearchCriteria, s.getSymbolId());
				final List<CandleTick> candleTicks = kiteDataService.extractData(data.getData(), s.getSymbol(),
						dataSearchCriteria.getPeriod());
				final Set<CandleTick> candleSet = checkOpenCloseStrategy(candleTicks);
				if (!CollectionUtils.isEmpty(candleSet))
					result.addAll(candleSet);
			});
		}
		if (CollectionUtils.isEmpty(result)) {
			return ResponseEntity.ok(HttpStatus.NO_CONTENT);
		}

		// return based on the highesh volume
		/// return new ResponseEntity<List<CandleTick>>(result.stream()
		/// .sorted(Comparator.comparing(CandleTick::getVolume).reversed()).collect(Collectors.toList()),
///				HttpStatus.OK);
		return new ResponseEntity<List<CandleTick>>(result, HttpStatus.OK);
	}

	private Set<CandleTick> checkOpenCloseStrategy(final List<CandleTick> candleTicks) {
		final Set<CandleTick> result = new LinkedHashSet<>();
		final LinkedList<CandleTick> ticks = new LinkedList<>(candleTicks);
		int count = 0;
		CandleTick prev = null;
		while (ticks.size() > count) {
			if (prev == null) {
				prev = ticks.getFirst();
			} else {
				CandleTick curr = ticks.get(count);
				if (curr.getOpen() > prev.getOpen() && curr.getClose() < prev.getClose()) {
					// find candles which has prev open < next day open
					// and next day close < prev close
					prev = curr;
					int i = count;
					i++;
					CandleTick next = null;

					if (i < ticks.size()) {
						next = ticks.get(i);
					}

					if (next != null && next.getOpen() < curr.getClose() && next.getClose() < next.getOpen()) {
						result.add(ticks.get(count));
					}
				}
			}
			count++;
		}
		return result;
	}

	private List<StockWatch> nse(final boolean type) {
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

		return data;
	}

}
