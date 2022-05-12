package com.ema.trading.steps;

import com.ema.trading.model.Symbol;
import com.ema.trading.repository.SymbolRepository;
import com.ema.trading.utils.SymbolInitializer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
@Qualifier("EmaReader")
public class EmaReader implements ItemReader<List<Symbol>> {

    @Autowired
    private SymbolRepository symbolRepository;

    @Autowired
    private SymbolInitializer symbolInitializer;

    @Override
    public List<Symbol> read() {
        final List<Symbol> symbols = symbolRepository.findAll();
        if (CollectionUtils.isEmpty(symbols)) {
            symbolInitializer.initApplication();
        }
        return symbols;
    }

}
