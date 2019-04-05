package com.data.service.dataservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.data.service.dataservice.external.NseService;
import com.data.service.dataservice.response.NiftyFityResponse;

@RequestMapping(value = "/api/nse")
public class NSEController {

	private static final Logger logger = LoggerFactory.getLogger(NSEController.class);

	@Autowired
	private NseService nseService;

	@GetMapping(value = "/nifty50")
	public ResponseEntity<?> nifty50() {
		logger.debug("Get the nifty 50");
		try {
			final List<NiftyFityResponse> list = nseService.getNiftyFifty();
			if (CollectionUtils.isEmpty(list)) {

			}
		} catch (Exception e) {
			logger.error("Failed to get nifty 50 stocks");
			return ResponseEntity.badRequest().body("Failed to get nifty 50 stocks ");
		}

		return ResponseEntity.ok(HttpStatus.OK);
	}
}
