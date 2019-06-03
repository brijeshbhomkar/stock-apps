package com.charting.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.springframework.http.ResponseEntity;

import com.charting.endpoints.DataServiceEndpoint;
import com.charting.json.SymbolResponse;
import com.charting.pojo.Symbol;
import com.charting.utils.RestfulSupport;

@ManagedBean(name = "symbolService", eager = true)
public class SymbolService extends RestfulSupport {

	private List<Symbol> symbols;

	@PostConstruct
	public void init() {
		symbols = new ArrayList<Symbol>();
		try {
			ResponseEntity<SymbolResponse> response = restTemplate.getForEntity(DataServiceEndpoint.GET_SYMBOL_URL,
					SymbolResponse.class);
			final SymbolResponse symbolResponse = response.getBody();
			symbols = symbolResponse.getSymbols();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Symbol> getSymbols() {
		return symbols;
	}

}
