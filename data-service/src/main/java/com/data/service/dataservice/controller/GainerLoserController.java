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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.data.service.dataservice.external.NSEService;
import com.data.service.dataservice.json.GainerLoser;
import com.data.service.dataservice.response.GainerLoserResponse;
import com.data.service.dataservice.util.EndpointUrls;

@RestController
@RequestMapping("/nse/nifty")
public class GainerLoserController {

	private static final Logger logger = LoggerFactory.getLogger(GainerLoserController.class);

	@Autowired
	private NSEService nseService;

	@RequestMapping("/gainers")
	public ResponseEntity<?> findGainers() {
		List<GainerLoser> stocks = new ArrayList<GainerLoser>();
		GainerLoserResponse response = null;
		try {
			response = nseService.getGainers(EndpointUrls.NSE_NIFTY_TOP_GAINER_1);
			if (response == null) {
				return ResponseEntity.badRequest().body(HttpStatus.NO_CONTENT);
			}
			if (!CollectionUtils.isEmpty(response.getData())) {
				stocks.addAll(response.getData());
			}

			response = nseService.getGainers(EndpointUrls.NSE_NIFTY_TOP_GAINER_2);
			if (response == null) {
				return ResponseEntity.badRequest().body(HttpStatus.NO_CONTENT);
			}
			if (!CollectionUtils.isEmpty(response.getData())) {
				stocks.addAll(response.getData());
			}
		} catch (Exception e) {
			logger.error("Failed to get nifty 50 stocks");
			return ResponseEntity.badRequest().body("Failed to get nifty 50 stocks watch");
		}

		return new ResponseEntity<List<GainerLoser>>(stocks.stream()
				.sorted(Comparator.comparing(GainerLoser::getLtp).reversed()).collect(Collectors.toList()),
				HttpStatus.OK);
	}

	@RequestMapping("/losers")
	public ResponseEntity<?> findLosers() {
		List<GainerLoser> stocks = new ArrayList<GainerLoser>();
		GainerLoserResponse response = null;
		try {
			response = nseService.getGainers(EndpointUrls.NSE_NIFTY_LOSER_1);
			if (response == null) {
				return ResponseEntity.badRequest().body(HttpStatus.NO_CONTENT);
			}
			if (!CollectionUtils.isEmpty(response.getData())) {
				stocks.addAll(response.getData());
			}

			response = nseService.getGainers(EndpointUrls.NSE_NIFTY_LOSER_2);
			if (response == null) {
				return ResponseEntity.badRequest().body(HttpStatus.NO_CONTENT);
			}
			if (!CollectionUtils.isEmpty(response.getData())) {
				stocks.addAll(response.getData());
			}
		} catch (Exception e) {
			logger.error("Failed to get nifty 50 stocks");
			return ResponseEntity.badRequest().body("Failed to get nifty 50 stocks watch");
		}

		return new ResponseEntity<List<GainerLoser>>(stocks.stream()
				.sorted(Comparator.comparing(GainerLoser::getLtp).reversed()).collect(Collectors.toList()),
				HttpStatus.OK);
	}
}
