package com.data.service.dataservice.controller;

import java.util.ArrayList;
import java.util.Comparator;
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

import com.data.service.dataservice.communication.GmailSender;
import com.data.service.dataservice.external.NSEService;
import com.data.service.dataservice.json.StockWatch;
import com.data.service.dataservice.response.StockWatchResponse;
import com.data.service.dataservice.util.EndpointUrls;

@RestController
@RequestMapping("/api/nse/stockwatch")
public class NSEStockWatchController {

	private static final Logger logger = LoggerFactory.getLogger(NSEStockWatchController.class);

	@Autowired
	private NSEService nseService;
	
	@Autowired
	private GmailSender gmailSender;
	
	@GetMapping("/intraday/upstocks/{limit}")
	public ResponseEntity<?> intradayUpstocks(@PathVariable final int limit) {
		final List<StockWatch> stocks = findUpstocks();
		if (CollectionUtils.isEmpty(stocks)) {
			return ResponseEntity.badRequest().body(HttpStatus.NO_CONTENT);
		}
		List<StockWatch> data = stocks.stream().distinct().collect(Collectors.toList());
		List<StockWatch> result = data.stream().sorted(Comparator.comparing(StockWatch::getTrdVol).reversed())
				.limit(limit == 0 ? 5 : limit).collect(Collectors.toList());
		
		//send an email 
		gmailSender.init(result);
		return new ResponseEntity<List<StockWatch>>(result, HttpStatus.OK);
	}

	private List<StockWatch> findUpstocks() {
		StockWatchResponse response = null;
		List<StockWatch> stocks = new ArrayList<>();

		// NSE_NIFTY_STOCKWATCH_50
		response = nseService.fetchNseStockWatch(EndpointUrls.NSE_NIFTY_STOCKWATCH_50);
		if (!CollectionUtils.isEmpty(response.getData())) {
			stocks.addAll(response.getData().stream().filter(s -> s.getOpen().equals(s.getLow()))
					.collect(Collectors.toList()));
		}

		// NSE_NIFTY_STOCKWATCH_NEXT_50
		response = nseService.fetchNseStockWatch(EndpointUrls.NSE_NIFTY_STOCKWATCH_NEXT_50);
		if (!CollectionUtils.isEmpty(response.getData())) {
			stocks.addAll(response.getData().stream().filter(s -> s.getOpen().equals(s.getLow()))
					.collect(Collectors.toList()));
		}

		// NSE_NIFTY_STOCKWATCH_MIDCAP_50
		response = nseService.fetchNseStockWatch(EndpointUrls.NSE_NIFTY_STOCKWATCH_MIDCAP_50);
		if (!CollectionUtils.isEmpty(response.getData())) {
			stocks.addAll(response.getData().stream().filter(s -> s.getOpen().equals(s.getLow()))
					.collect(Collectors.toList()));
		}

		// NSE_NIFTY_STOCKWATCH_MIDCAP_150
		response = nseService.fetchNseStockWatch(EndpointUrls.NSE_NIFTY_STOCKWATCH_MIDCAP_150);
		if (!CollectionUtils.isEmpty(response.getData())) {
			stocks.addAll(response.getData().stream().filter(s -> s.getOpen().equals(s.getLow()))
					.collect(Collectors.toList()));
		}

		// NSE_NIFTY_STOCKWATCH_SMALCAP_50
		response = nseService.fetchNseStockWatch(EndpointUrls.NSE_NIFTY_STOCKWATCH_SMALCAP_50);
		if (!CollectionUtils.isEmpty(response.getData())) {
			stocks.addAll(response.getData().stream().filter(s -> s.getOpen().equals(s.getLow()))
					.collect(Collectors.toList()));
		}

		// NSE_NIFTY_STOCKWATCH_SMALCAP_50
		response = nseService.fetchNseStockWatch(EndpointUrls.NSE_NIFTY_STOCKWATCH_SMALCAP_250);
		if (!CollectionUtils.isEmpty(response.getData())) {
			stocks.addAll(response.getData().stream().filter(s -> s.getOpen().equals(s.getLow()))
					.collect(Collectors.toList()));
		}

		// NSE_NIFTY_STOCKWATCH_MIDCAP_400
		response = nseService.fetchNseStockWatch(EndpointUrls.NSE_NIFTY_STOCKWATCH_MIDCAP_400);
		if (!CollectionUtils.isEmpty(response.getData())) {
			stocks.addAll(response.getData().stream().filter(s -> s.getOpen().equals(s.getLow()))
					.collect(Collectors.toList()));
		}
		return stocks;
	}

	private List<StockWatch> findDownstocks() {
		StockWatchResponse response = null;
		List<StockWatch> stocks = new ArrayList<>();

		// NSE_NIFTY_STOCKWATCH_50
		response = nseService.fetchNseStockWatch(EndpointUrls.NSE_NIFTY_STOCKWATCH_50);
		if (!CollectionUtils.isEmpty(response.getData())) {
			stocks.addAll(response.getData().stream().filter(s -> s.getOpen().equals(s.getHigh()))
					.collect(Collectors.toList()));
		}

		// NSE_NIFTY_STOCKWATCH_NEXT_50
		response = nseService.fetchNseStockWatch(EndpointUrls.NSE_NIFTY_STOCKWATCH_NEXT_50);
		if (!CollectionUtils.isEmpty(response.getData())) {
			stocks.addAll(response.getData().stream().filter(s -> s.getOpen().equals(s.getHigh()))
					.collect(Collectors.toList()));
		}

		// NSE_NIFTY_STOCKWATCH_MIDCAP_50
		response = nseService.fetchNseStockWatch(EndpointUrls.NSE_NIFTY_STOCKWATCH_MIDCAP_50);
		if (!CollectionUtils.isEmpty(response.getData())) {
			stocks.addAll(response.getData().stream().filter(s -> s.getOpen().equals(s.getHigh()))
					.collect(Collectors.toList()));
		}

		// NSE_NIFTY_STOCKWATCH_MIDCAP_150
		response = nseService.fetchNseStockWatch(EndpointUrls.NSE_NIFTY_STOCKWATCH_MIDCAP_150);
		if (!CollectionUtils.isEmpty(response.getData())) {
			stocks.addAll(response.getData().stream().filter(s -> s.getOpen().equals(s.getHigh()))
					.collect(Collectors.toList()));
		}

		// NSE_NIFTY_STOCKWATCH_SMALCAP_50
		response = nseService.fetchNseStockWatch(EndpointUrls.NSE_NIFTY_STOCKWATCH_SMALCAP_50);
		if (!CollectionUtils.isEmpty(response.getData())) {
			stocks.addAll(response.getData().stream().filter(s -> s.getOpen().equals(s.getHigh()))
					.collect(Collectors.toList()));
		}

		// NSE_NIFTY_STOCKWATCH_SMALCAP_50
		response = nseService.fetchNseStockWatch(EndpointUrls.NSE_NIFTY_STOCKWATCH_SMALCAP_250);
		if (!CollectionUtils.isEmpty(response.getData())) {
			stocks.addAll(response.getData().stream().filter(s -> s.getOpen().equals(s.getHigh()))
					.collect(Collectors.toList()));
		}

		// NSE_NIFTY_STOCKWATCH_MIDCAP_400
		response = nseService.fetchNseStockWatch(EndpointUrls.NSE_NIFTY_STOCKWATCH_MIDCAP_400);
		if (!CollectionUtils.isEmpty(response.getData())) {
			stocks.addAll(response.getData().stream().filter(s -> s.getOpen().equals(s.getHigh()))
					.collect(Collectors.toList()));
		}
		return stocks;
	}

	@RequestMapping("/intraday/downstocks/{limit}")
	public ResponseEntity<?> intradayDownStocks(@PathVariable final int limit) {
		final List<StockWatch> stocks = findDownstocks();
		if (CollectionUtils.isEmpty(stocks)) {
			return ResponseEntity.badRequest().body(HttpStatus.NO_CONTENT);
		}
		List<StockWatch> data = stocks.stream().distinct().collect(Collectors.toList());
		List<StockWatch> result = data.stream().sorted(Comparator.comparing(StockWatch::getTrdVol).reversed())
				.limit(limit == 0 ? 5 : limit).collect(Collectors.toList());
		return new ResponseEntity<List<StockWatch>>(result, HttpStatus.OK);
	}

	@RequestMapping("/nifty/50")
	public ResponseEntity<?> niftyEquity50() {
		logger.debug("Get the nifty 50");
		List<StockWatch> stocks = new ArrayList<StockWatch>();
		try {
			StockWatchResponse response = nseService.fetchNseStockWatch(EndpointUrls.NSE_NIFTY_STOCKWATCH_50);
			if (response == null) {
				return ResponseEntity.badRequest().body(HttpStatus.NO_CONTENT);
			}
			final List<StockWatch> stockData = response.getData();
			if (!CollectionUtils.isEmpty(stockData)) {
				stocks = stockData.stream().filter(s -> s.getOpen().equals(s.getLow())).collect(Collectors.toList());
			}
		} catch (Exception e) {
			logger.error("Failed to get nifty 50 stocks");
			return ResponseEntity.badRequest().body("Failed to get nifty 50 stocks watch");
		}

		return new ResponseEntity<List<StockWatch>>(stocks, HttpStatus.OK);
	}

	@RequestMapping("/nifty/next/50")
	public ResponseEntity<?> niftyEquityNext50() {
		List<StockWatch> stocks = new ArrayList<StockWatch>();
		try {
			StockWatchResponse response = nseService.fetchNseStockWatch(EndpointUrls.NSE_NIFTY_STOCKWATCH_NEXT_50);
			if (response == null) {
				return ResponseEntity.badRequest().body(HttpStatus.NO_CONTENT);
			}
			final List<StockWatch> stockData = response.getData();
			if (!CollectionUtils.isEmpty(stockData)) {
				stocks = stockData.stream().filter(s -> s.getOpen().equals(s.getLow())).collect(Collectors.toList());
			}
		} catch (Exception e) {
			logger.error("Failed to get stocks");
			return ResponseEntity.badRequest().body("Failed to get stocks");
		}

		return new ResponseEntity<List<StockWatch>>(stocks, HttpStatus.OK);
	}

	@RequestMapping("/nifty/midcap/50")
	public ResponseEntity<?> niftyEquityMidcap50() {
		List<StockWatch> stocks = new ArrayList<StockWatch>();
		try {
			StockWatchResponse response = nseService.fetchNseStockWatch(EndpointUrls.NSE_NIFTY_STOCKWATCH_MIDCAP_50);
			if (response == null) {
				return ResponseEntity.badRequest().body(HttpStatus.NO_CONTENT);
			}
			final List<StockWatch> stockData = response.getData();
			if (!CollectionUtils.isEmpty(stockData)) {
				stocks = stockData.stream().filter(s -> s.getOpen().equals(s.getLow())).collect(Collectors.toList());
			}
		} catch (Exception e) {
			logger.error("Failed to get stocks");
			return ResponseEntity.badRequest().body("Failed to get stocks");
		}

		return new ResponseEntity<List<StockWatch>>(stocks, HttpStatus.OK);
	}

	@RequestMapping("/nifty/midcap/150")
	public ResponseEntity<?> niftyEquityMidcap150() {
		List<StockWatch> stocks = new ArrayList<StockWatch>();
		try {
			StockWatchResponse response = nseService.fetchNseStockWatch(EndpointUrls.NSE_NIFTY_STOCKWATCH_MIDCAP_150);
			if (response == null) {
				return ResponseEntity.badRequest().body(HttpStatus.NO_CONTENT);
			}
			final List<StockWatch> stockData = response.getData();
			if (!CollectionUtils.isEmpty(stockData)) {
				stocks = stockData.stream().filter(s -> s.getOpen().equals(s.getLow())).collect(Collectors.toList());
			}
		} catch (Exception e) {
			logger.error("Failed to get stocks");
			return ResponseEntity.badRequest().body("Failed to get stocks");
		}

		return new ResponseEntity<List<StockWatch>>(stocks, HttpStatus.OK);
	}

	@RequestMapping("/nifty/smallcap/50")
	public ResponseEntity<?> niftyEquitySmallCap50() {
		List<StockWatch> stocks = new ArrayList<StockWatch>();
		try {
			StockWatchResponse response = nseService.fetchNseStockWatch(EndpointUrls.NSE_NIFTY_STOCKWATCH_SMALCAP_50);
			if (response == null) {
				return ResponseEntity.badRequest().body(HttpStatus.NO_CONTENT);
			}
			final List<StockWatch> stockData = response.getData();
			if (!CollectionUtils.isEmpty(stockData)) {
				stocks = stockData.stream().filter(s -> s.getOpen().equals(s.getLow())).collect(Collectors.toList());
			}
		} catch (Exception e) {
			logger.error("Failed to get stocks");
			return ResponseEntity.badRequest().body("Failed to get stocks");
		}

		return new ResponseEntity<List<StockWatch>>(stocks, HttpStatus.OK);
	}

	@RequestMapping("/nifty/smallcap/250")
	public ResponseEntity<?> niftyEquitySmallCap250() {
		List<StockWatch> stocks = new ArrayList<StockWatch>();
		try {
			StockWatchResponse response = nseService.fetchNseStockWatch(EndpointUrls.NSE_NIFTY_STOCKWATCH_SMALCAP_250);
			if (response == null) {
				return ResponseEntity.badRequest().body(HttpStatus.NO_CONTENT);
			}
			final List<StockWatch> stockData = response.getData();
			if (!CollectionUtils.isEmpty(stockData)) {
				stocks = stockData.stream().filter(s -> s.getOpen().equals(s.getLow())).collect(Collectors.toList());
			}
		} catch (Exception e) {
			logger.error("Failed to get stocks");
			return ResponseEntity.badRequest().body("Failed to get stocks");
		}

		return new ResponseEntity<List<StockWatch>>(stocks, HttpStatus.OK);
	}

	@RequestMapping("/nifty/midcap/400")
	public ResponseEntity<?> niftyEquityMidCap400() {
		List<StockWatch> stocks = new ArrayList<StockWatch>();
		try {
			StockWatchResponse response = nseService.fetchNseStockWatch(EndpointUrls.NSE_NIFTY_STOCKWATCH_MIDCAP_400);
			if (response == null) {
				return ResponseEntity.badRequest().body(HttpStatus.NO_CONTENT);
			}
			final List<StockWatch> stockData = response.getData();
			if (!CollectionUtils.isEmpty(stockData)) {
				stocks = stockData.stream().filter(s -> s.getOpen().equals(s.getLow())).collect(Collectors.toList());
			}
		} catch (Exception e) {
			logger.error("Failed to get stocks");
			return ResponseEntity.badRequest().body("Failed to get stocks");
		}

		return new ResponseEntity<List<StockWatch>>(stocks, HttpStatus.OK);
	}

}
