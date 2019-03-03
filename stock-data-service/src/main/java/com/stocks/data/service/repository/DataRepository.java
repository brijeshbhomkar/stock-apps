package com.stocks.data.service.repository;

import com.stocks.data.service.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The data repository.
 */
@Repository
public interface DataRepository extends JpaRepository<Stock, Long> {
}
