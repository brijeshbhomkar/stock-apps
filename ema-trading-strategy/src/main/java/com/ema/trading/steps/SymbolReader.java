package com.ema.trading.steps;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ema.trading.model.Symbol;
import com.ema.trading.repository.SymbolRepository;

@Component
@Qualifier("symbolReader")
public class SymbolReader implements ItemReader<List<Symbol>> {

	@Autowired
	private SymbolRepository symbolRepository;

	@Override
	public List<Symbol> read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		final List<Symbol> symbols = symbolRepository.findAll();
		return CollectionUtils.isEmpty(symbols) != Boolean.FALSE ? symbols : null;
	}

}
