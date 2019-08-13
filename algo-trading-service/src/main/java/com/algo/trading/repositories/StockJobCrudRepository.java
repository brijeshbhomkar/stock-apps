package com.algo.trading.repositories;

import com.algo.trading.entities.StockJob;

public interface StockJobCrudRepository {
	public StockJob findStockJobById(final String id);
	public boolean updateStockJobStatus(final String id, final String state);
}
