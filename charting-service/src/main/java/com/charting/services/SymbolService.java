package com.charting.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.charting.utils.RestfulSupport;

public class SymbolService extends RestfulSupport {

	public List<String> getSymbols() {
		List<String> symbols = new ArrayList<String>();
		try {
		ResponseEntity<String[]> response = restTemplate.getForEntity("http://localhost:12020/api/symbol/",
				String[].class);
		symbols =  Arrays.asList(response.getBody());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return symbols;
	}
}
