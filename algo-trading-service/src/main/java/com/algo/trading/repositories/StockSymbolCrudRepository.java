package com.algo.trading.repositories;

import com.algo.trading.entities.Symbol;

public interface StockSymbolCrudRepository {
	Symbol findSymbol(final String symbol);
}
