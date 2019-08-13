package com.algo.trading.repositories;

import com.algo.trading.entities.Retracement;

public interface RetracementCrudRepository {
	public Retracement findRetracementById(final String symbolId);
}
