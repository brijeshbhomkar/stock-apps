package com.algo.trading.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algo.trading.entities.StockJob;

@Repository
public interface StockJobRepository extends JpaRepository<StockJob, Double>, StockJobCrudRepository {
}
