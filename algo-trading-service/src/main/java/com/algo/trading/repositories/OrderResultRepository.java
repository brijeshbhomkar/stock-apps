package com.algo.trading.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algo.trading.entities.OrderResult;

@Repository
public interface OrderResultRepository extends JpaRepository<OrderResult, Long> {

}
