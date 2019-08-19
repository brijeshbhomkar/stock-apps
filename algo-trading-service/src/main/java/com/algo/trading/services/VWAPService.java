package com.algo.trading.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.algo.trading.entities.Symbol;
import com.algo.trading.repositories.SymbolRepository;
import com.algo.trading.repositories.VWAPRepository;

@Service
public class VWAPService {
	
	@Autowired
	private VWAPRepository vwapRepository;
	
	@Autowired
	private DataFetchService dataFetchService;
	
	@Autowired
	private SymbolRepository symbolRepository;
	
	List<Symbol> symbols = null;
	
	@Scheduled(cron="* 1 * * * 1-5")
	public void start() {
		if (symbols == null) {
			symbols = symbolRepository.findAll();
			
		}
	}
}
