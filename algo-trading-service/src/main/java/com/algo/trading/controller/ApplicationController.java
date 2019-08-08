package com.algo.trading.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algo.trading.core.FibonacciRetracementTrading;

@RestController
@RequestMapping("/api/algo")
public class ApplicationController {

	@Autowired
	private FibonacciRetracementTrading fibonacciService;

	@RequestMapping("/fibo/{id}")
	public ResponseEntity<?> start(@PathVariable final String id) {
		fibonacciService.process(id);
		return ResponseEntity.ok("Started processing " + id);
	}
}
