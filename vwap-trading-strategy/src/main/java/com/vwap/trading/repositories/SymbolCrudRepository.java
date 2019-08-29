package com.vwap.trading.repositories;

import com.vwap.trading.models.Symbol;

public interface SymbolCrudRepository {
	Symbol findSymbol(final String symbol);
}
