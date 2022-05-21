package com.nse.services.weekly.repository;

import com.nse.services.weekly.model.YearlyHighStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YearlyHighStockRepository extends JpaRepository<YearlyHighStock, Long> {
}
