package com.ema.trading.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ema.trading.model.EMA8;

@Repository
public interface Ema8Repository extends JpaRepository<EMA8, Long>{
}
