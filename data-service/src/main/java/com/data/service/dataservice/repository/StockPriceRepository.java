package com.data.service.dataservice.repository;

import java.util.List;

import com.data.service.dataservice.entity.Stocks;

public interface StockPriceRepository {
	List<Stocks> findPriceBetweenRange(final Double upparPrice, final Double lowerPrice);
}
