package com.algo.trading.services;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algo.trading.entities.Symbol;
import com.algo.trading.repositories.SymbolRepository;
import com.algo.trading.utils.SymbolMapper;

@Component
public class SymbolLoadingService {

	private static final Logger logger = LoggerFactory.getLogger(SymbolLoadingService.class);

	@Autowired
	private SymbolRepository symbolRepository;

	@PostConstruct
	public void init() {
		try {
		SymbolMapper.getSymbolMapper().entrySet().forEach(s -> {
			final Symbol symbol = new Symbol();
			symbol.setExchange("nse");
			symbol.setSymbol(s.getKey());
			symbol.setSymbolId(Long.parseLong(s.getValue()));
			logger.debug("adding symbol " + symbol.toString());
			symbolRepository.saveAndFlush(symbol);
		});
		} catch (Exception e) {
			logger.error("Failed to update symbols ", e);
		}
	}

	@PreDestroy
	public void destroy() {
		symbolRepository.deleteAll();
	}
}
