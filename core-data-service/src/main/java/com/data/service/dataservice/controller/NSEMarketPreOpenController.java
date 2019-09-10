package com.data.service.dataservice.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.data.service.dataservice.entity.PreOpenStock;
import com.data.service.dataservice.external.NSEService;
import com.data.service.dataservice.json.PreOpen;
import com.data.service.dataservice.repository.NSERepository;
import com.data.service.dataservice.response.PreOpenResponse;
import com.data.service.dataservice.searchcriteria.PriceRangeCriteria;
import com.data.service.dataservice.util.EndpointUrls;
import com.data.service.dataservice.util.NSEStockType;

@RestController
@RequestMapping(value = "/api/nse/preopen")
public class NSEMarketPreOpenController {

	private static final Logger logger = LoggerFactory.getLogger(NSEMarketPreOpenController.class);

	@Autowired
	private NSEService nseService;

	@Autowired
	private NSERepository nseRepository;

	@GetMapping("/nifty50")
	public ResponseEntity<?> nifty50() {
		logger.debug("Get the nifty 50");
		List<PreOpenStock> stocks = new ArrayList<PreOpenStock>();
		try {
			PreOpenResponse response = nseService.fetchNsePreOpenStock(EndpointUrls.NSE_NIFTY_PREOPEN_50_URL);
			if (response == null) {
				return ResponseEntity.badRequest().body(HttpStatus.NO_CONTENT);
			}
			final List<PreOpen> stockData = response.getData();
			if (!CollectionUtils.isEmpty(stockData)) {
				stocks = (List<PreOpenStock>) extractData(stockData, NSEStockType.NSE_FITY);
				for (PreOpenStock stock : stocks) {
					nseRepository.save(stock);
				}
			}
		} catch (Exception e) {
			logger.error("Failed to get nifty 50 stocks");
			return ResponseEntity.badRequest().body("Failed to get nifty 50 stocks ");
		}

		return new ResponseEntity<List<PreOpenStock>>(stocks, HttpStatus.OK);
	}

	@GetMapping("/fostocks")
	public ResponseEntity<?> foStocks() {
		logger.debug("Get the fo stocks");
		List<PreOpenStock> result = new ArrayList<PreOpenStock>();
		try {
			PreOpenResponse response = nseService.fetchNsePreOpenStock(EndpointUrls.NSE_NIFTY_PREOPEN_FO_URL);
			if (response == null) {
				return ResponseEntity.badRequest().body(HttpStatus.NO_CONTENT);
			}
			final List<PreOpen> stocks = response.getData();
			if (!CollectionUtils.isEmpty(stocks)) {
				result = extractData(response.getData(), NSEStockType.NSE_FO);
				for (PreOpenStock stock : result) {
					nseRepository.save(stock);
				}
			}
		} catch (Exception e) {
			logger.error("Failed to get nifty fo stocks");
			return ResponseEntity.badRequest().body("Failed to get nifty fo stocks ");
		}

		return new ResponseEntity<List<PreOpenStock>>(result, HttpStatus.OK);
	}

	@GetMapping("/niftybanks")
	public ResponseEntity<?> niftyBanks() {
		logger.debug("Get the nifty banks stocks");
		List<PreOpenStock> result = new ArrayList<PreOpenStock>();
		try {
			PreOpenResponse response = nseService.fetchNsePreOpenStock(EndpointUrls.NSE_NIFTY_PREOPEN_BANK_URL);
			if (response == null) {
				return ResponseEntity.badRequest().body(HttpStatus.NO_CONTENT);
			}
			final List<PreOpen> stocks = response.getData();
			if (!CollectionUtils.isEmpty(stocks)) {
				result = extractData(response.getData(), NSEStockType.NSE_BANK);
				for (PreOpenStock stock : result) {
					nseRepository.save(stock);
				}
			}
		} catch (Exception e) {
			logger.error("Failed to get nifty fo stocks");
			return ResponseEntity.badRequest().body("Failed to get nifty fo stocks ");
		}

		return new ResponseEntity<List<PreOpenStock>>(result, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<?> niftyAll() {
		logger.debug("Get the nifty all stocks");
		List<PreOpenStock> result = new ArrayList<PreOpenStock>();
		try {
			PreOpenResponse response = nseService.fetchNsePreOpenStock(EndpointUrls.NSE_NIFTY_PREOPEN_ALL_URL);
			if (response == null) {
				return ResponseEntity.badRequest().body(HttpStatus.NO_CONTENT);
			}
			final List<PreOpen> stocks = response.getData();
			if (!CollectionUtils.isEmpty(stocks)) {
				result = (List<PreOpenStock>) extractData(response.getData(), NSEStockType.NSE_ALL);
				for (PreOpenStock stock : result) {
					nseRepository.save(stock);
				}
			}
		} catch (Exception e) {
			logger.error("Failed to get nifty fo stocks");
			return ResponseEntity.badRequest().body("Failed to get nifty fo stocks ");
		}

		return new ResponseEntity<List<PreOpenStock>>(result, HttpStatus.OK);
	}

	private List<PreOpenStock> extractData(final List<PreOpen> data, final NSEStockType type) {
		List<PreOpenStock> stocks = new ArrayList<>();
		if (type.getType().equals(NSEStockType.NSE_FITY.getType())) {
			for (PreOpen stock : data) {
				final PreOpenStock stockData = new PreOpenStock();
				BeanUtils.copyProperties(stock, stockData);
				stockData.setType(NSEStockType.NSE_ALL.getType());
				stocks.add(stockData);
			}
		} else if (type.getType().equals(NSEStockType.NSE_BANK.getType())) {
			for (PreOpen stock : data) {
				final PreOpenStock stockData = new PreOpenStock();
				BeanUtils.copyProperties(stock, stockData);
				stockData.setType(NSEStockType.NSE_ALL.getType());
				stocks.add(stockData);
			}
		} else if (type.getType().equals(NSEStockType.NSE_FO.getType())) {
			for (PreOpen stock : data) {
				final PreOpenStock stockData = new PreOpenStock();
				BeanUtils.copyProperties(stock, stockData);
				stockData.setType(NSEStockType.NSE_ALL.getType());
				stocks.add(stockData);
			}
		} else if (type.getType().equals(NSEStockType.NSE_ALL.getType())) {
			for (PreOpen stock : data) {
				final PreOpenStock stockData = new PreOpenStock();
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

	@GetMapping("/clean")
	public ResponseEntity<?> clean() {
		try {
			nseRepository.deleteAll();
		} catch (Exception e) {
			logger.error("Failed to clean all tables");
			return ResponseEntity.badRequest().body("Failed to clean all tables");
		}

		return ResponseEntity.ok("Cleaned!");
	}

	@PostMapping("/pricerange")
	public ResponseEntity<?> betweenPriceRange(@RequestBody final PriceRangeCriteria priceRangeCriteria) {
		Set<PreOpenStock> stocks = new HashSet<>();
		try {
			final List<PreOpenStock> result = nseRepository.findPriceBetweenRange(
					Double.valueOf(priceRangeCriteria.getLowerBound()),
					Double.valueOf(priceRangeCriteria.getUpparBound()));
			stocks.addAll(result);
		} catch (Exception e) {
			logger.error("Failed to get the stock between prices ");
			return ResponseEntity.badRequest().body("Failed to get the stock between prices ");
		}
		return new ResponseEntity<Set<PreOpenStock>>(stocks, HttpStatus.OK);
	}

	@GetMapping("/db/all")
	public ResponseEntity<?> findAll() {
		List<PreOpenStock> stocks = new ArrayList<>();
		try {
			stocks = nseRepository.findAll();
		} catch (Exception e) {
			logger.error("Failed to find stocks from db ");
			return ResponseEntity.badRequest().body("Failed to find stocks from db ");
		}
		return new ResponseEntity<List<PreOpenStock>>(stocks, HttpStatus.OK);
	}

	@GetMapping("/50")
	public ResponseEntity<?> nseFifty() {
		List<PreOpenStock> stocks = nseRepository.findAll();
		if (CollectionUtils.isEmpty(stocks)) {
			return ResponseEntity.badRequest().body(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<PreOpenStock>>(stocks, HttpStatus.OK);
	}
}
