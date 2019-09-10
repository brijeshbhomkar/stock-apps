package com.data.service.dataservice.repository;

import java.util.List;

import com.data.service.dataservice.entity.PreOpenStock;

public interface StockPriceRepository {
	List<PreOpenStock> findPriceBetweenRange(final Double upparPrice, final Double lowerPrice);
}
