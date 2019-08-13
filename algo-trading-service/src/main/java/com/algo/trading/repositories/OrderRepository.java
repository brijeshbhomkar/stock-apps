package com.algo.trading.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algo.trading.entities.StockOrder;

@Repository
public interface OrderRepository extends JpaRepository<StockOrder, Long> {

}
