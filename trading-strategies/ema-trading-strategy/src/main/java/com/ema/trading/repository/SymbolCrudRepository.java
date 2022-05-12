package com.ema.trading.repository;

import com.ema.trading.model.Symbol;

public interface SymbolCrudRepository {
	Symbol findSymbol(final String symbol);
}
