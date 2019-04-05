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

import com.data.service.dataservice.entity.Stock;
import com.data.service.dataservice.external.NSEService;
import com.data.service.dataservice.repository.NSERepository;
import com.data.service.dataservice.response.StockDataResponse;
import com.data.service.dataservice.response.StockData;

@RequestMapping(value = "/api/nse")
public class NSEController {

	private static final Logger logger = LoggerFactory.getLogger(NSEController.class);

	@Autowired
	private NSEService nseService;

	@Autowired
	private NSERepository nseRepository;

	@GetMapping(value = "/nifty50")
	public ResponseEntity<?> nifty50() {
		logger.debug("Get the nifty 50");
		try {
			StockDataResponse response = nseService.getNiftyFifty();
			if (response == null) {
				return ResponseEntity.badRequest().body(HttpStatus.NO_CONTENT);
			}
			final List<StockData> stocks = response.getData();
			if (!CollectionUtils.isEmpty(stocks)) {
				final List<Stock> result = extract(response.getData());
				for (Stock stock : result) {
					nseRepository.save(stock);
				}
			}
		} catch (Exception e) {
			logger.error("Failed to get nifty 50 stocks");
			return ResponseEntity.badRequest().body("Failed to get nifty 50 stocks ");
		}

		return ResponseEntity.ok(HttpStatus.OK);
	}

	private List<Stock> extract(final List<StockData> data) {
		List<Stock> stocks = new ArrayList<Stock>();
		for (StockData stock : data) {
			final Stock stockData = new Stock();
			BeanUtils.copyProperties(stock, stockData);
			stocks.add(stockData);
		}
		return stocks;
	}

}
