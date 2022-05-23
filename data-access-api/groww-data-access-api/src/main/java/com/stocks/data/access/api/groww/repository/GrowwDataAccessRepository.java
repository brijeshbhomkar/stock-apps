package com.stocks.data.access.api.groww.repository;

import com.stocks.data.access.api.groww.model.StockTick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrowwDataAccessRepository extends JpaRepository<StockTick, Long> {
}
