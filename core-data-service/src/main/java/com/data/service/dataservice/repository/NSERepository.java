package com.data.service.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.service.dataservice.entity.PreOpenStock;

public interface NSERepository extends JpaRepository<PreOpenStock, Long>, StockPriceRepository {
}
