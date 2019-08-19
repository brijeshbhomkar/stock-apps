package com.algo.trading.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algo.trading.entities.VWAP;

@Repository
public interface VWAPRepository extends JpaRepository<VWAP, Long> {

}
