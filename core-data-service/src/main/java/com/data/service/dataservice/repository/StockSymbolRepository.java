package com.data.service.dataservice.repository;

import com.data.service.dataservice.entity.Symbol;

public interface StockSymbolRepository {
	Symbol findSymbol(final String symbol);
}
