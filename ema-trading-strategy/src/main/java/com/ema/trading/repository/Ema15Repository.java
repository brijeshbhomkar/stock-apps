package com.ema.trading.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ema.trading.model.EMA15;

@Repository
public interface Ema15Repository extends JpaRepository<EMA15, Long>{

}
