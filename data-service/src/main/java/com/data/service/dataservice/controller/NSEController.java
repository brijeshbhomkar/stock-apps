package com.data.service.dataservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.data.service.dataservice.entity.Stocks;
import com.data.service.dataservice.external.NSEService;
import com.data.service.dataservice.repository.NSERepository;
import com.data.service.dataservice.response.StockData;
import com.data.service.dataservice.response.StockDataResponse;
import com.data.service.dataservice.util.EndpointUrls;
import com.data.service.dataservice.util.NSEStockType;

@RestController
@RequestMapping(value = "/api/nse")
public class NSEController {

	private static final Logger logger = LoggerFactory.getLogger(NSEController.class);

	@Autowired
	private NSEService nseService;

	@Autowired
	private NSERepository nseRepository;

	@GetMapping("/nifty50")
	public ResponseEntity<?> nifty50() {
		logger.debug("Get the nifty 50");
		try {
			StockDataResponse response = nseService.fetchDataFromNse(EndpointUrls.NSE_NIFTY_50_URL);
			if (response == null) {
				return ResponseEntity.badRequest().body(HttpStatus.NO_CONTENT);
			}
			final List<StockData> stocks = response.getData();
			if (!CollectionUtils.isEmpty(stocks)) {
				final List<Stocks> result = (List<Stocks>) extractData(response.getData(), NSEStockType.NSE_FITY);
				for (Stocks stock : result) {
					nseRepository.save(stock);
				}
			}
		} catch (Exception e) {
			logger.error("Failed to get nifty 50 stocks");
			return ResponseEntity.badRequest().body("Failed to get nifty 50 stocks ");
		}

		return ResponseEntity.ok(HttpStatus.OK);
	}

	@GetMapping("/fostocks")
	public ResponseEntity<?> foStocks() {
		logger.debug("Get the fo stocks");
		try {
			StockDataResponse response = nseService.fetchDataFromNse(EndpointUrls.NSE_NIFTY_FO_URL);
			if (response == null) {
				return ResponseEntity.badRequest().body(HttpStatus.NO_CONTENT);
			}
			final List<StockData> stocks = response.getData();
			if (!CollectionUtils.isEmpty(stocks)) {
				final List<Stocks> result = extractData(response.getData(), NSEStockType.NSE_FO);
				for (Stocks stock : result) {
					nseRepository.save(stock);
				}
			}
		} catch (Exception e) {
			logger.error("Failed to get nifty fo stocks");
			return ResponseEntity.badRequest().body("Failed to get nifty fo stocks ");
		}

		return ResponseEntity.ok(HttpStatus.OK);
	}

	@GetMapping("/niftybanks")
	public ResponseEntity<?> niftyBanks() {
		logger.debug("Get the nifty banks stocks");
		try {
			StockDataResponse response = nseService.fetchDataFromNse(EndpointUrls.NSE_NIFTY_BANK_URL);
			if (response == null) {
				return ResponseEntity.badRequest().body(HttpStatus.NO_CONTENT);
			}
			final List<StockData> stocks = response.getData();
			if (!CollectionUtils.isEmpty(stocks)) {
				final List<Stocks> result = extractData(response.getData(), NSEStockType.NSE_BANK);
				for (Stocks stock : result) {
					nseRepository.save(stock);
				}
			}
		} catch (Exception e) {
			logger.error("Failed to get nifty fo stocks");
			return ResponseEntity.badRequest().body("Failed to get nifty fo stocks ");
		}

		return ResponseEntity.ok(HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<?> niftyAll() {
		logger.debug("Get the nifty all stocks");
		try {
			StockDataResponse response = nseService.fetchDataFromNse(EndpointUrls.NSE_NIFTY_ALL_URL);
			if (response == null) {
				return ResponseEntity.badRequest().body(HttpStatus.NO_CONTENT);
			}
			final List<StockData> stocks = response.getData();
			if (!CollectionUtils.isEmpty(stocks)) {
				final List<Stocks> result = (List<Stocks>) extractData(response.getData(), NSEStockType.NSE_ALL);
				for (Stocks stock : result) {
					nseRepository.save(stock);
				}
			}
		} catch (Exception e) {
			logger.error("Failed to get nifty fo stocks");
			return ResponseEntity.badRequest().body("Failed to get nifty fo stocks ");
		}

		return ResponseEntity.ok(HttpStatus.OK);
	}

	private List<Stocks> extractData(final List<StockData> data, final NSEStockType type) {
		List<Stocks> stocks = new ArrayList<>();
		if (type.getType().equals(NSEStockType.NSE_FITY.getType())) {
			for (StockData stock : data) {
				final Stocks stockData = new Stocks();
				BeanUtils.copyProperties(stock, stockData);
				stockData.setType(NSEStockType.NSE_ALL.getType());
				stocks.add(stockData);
			}
		} else if (type.getType().equals(NSEStockType.NSE_BANK.getType())) {
			for (StockData stock : data) {
				final Stocks stockData = new Stocks();
				BeanUtils.copyProperties(stock, stockData);
				stockData.setType(NSEStockType.NSE_ALL.getType());
				stocks.add(stockData);
			}
		} else if (type.getType().equals(NSEStockType.NSE_FO.getType())) {
			for (StockData stock : data) {
				final Stocks stockData = new Stocks();
				BeanUtils.copyProperties(stock, stockData);
				stockData.setType(NSEStockType.NSE_ALL.getType());
				stocks.add(stockData);
			}
		} else if (type.getType().equals(NSEStockType.NSE_ALL.getType())) {
			for (StockData stock : data) {
				final Stocks stockData = new Stocks();
				BeanUtils.copyProperties(stock, stockData);
				stockData.setType(NSEStockType.NSE_ALL.getType());
				stocks.add(stockData);
			}
		}
		return stocks;
	}

	@GetMapping("/ping")
	public ResponseEntity<?> ping() {
		return ResponseEntity.ok(HttpStatus.OK);
	}

}
