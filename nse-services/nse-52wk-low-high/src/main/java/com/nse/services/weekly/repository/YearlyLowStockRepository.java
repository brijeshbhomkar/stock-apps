package com.nse.services.weekly.repository;

import com.nse.services.weekly.model.YearlyLowStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YearlyLowStockRepository extends JpaRepository<YearlyLowStock, Long> {
}
