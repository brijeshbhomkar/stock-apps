package com.vwap.trading.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vwap.trading.models.Vwap;

@Repository
public interface VwapRepository extends JpaRepository<Vwap, Long> {

}
