package com.data.service.dataservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/strategy/macd")
public class MACDController {
	
	@GetMapping
	public ResponseEntity<?> macd() {
		return null;
	}
}
