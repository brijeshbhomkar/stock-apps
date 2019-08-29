package com.ema.trading.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ema.trading.model.EMA;

@Repository
public interface EmaRepository extends JpaRepository<EMA, Long>{

}
