package com.data.service.dataservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
