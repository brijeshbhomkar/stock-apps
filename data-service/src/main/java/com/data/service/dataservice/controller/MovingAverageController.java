package com.data.service.dataservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.data.service.dataservice.technicalanalysis.TechnicalAnalysis;

@RestController
@RequestMapping("/api/ta/ma")
public class MovingAverageController {

	private static final Logger log = LoggerFactory.getLogger(MovingAverageController.class);

	@PostMapping
	public ResponseEntity<?> movingAverage(@RequestBody final TechnicalAnalysis technicalAnalysis) {
		log.debug("Find the technical analysis");
		return null;
	}
}
