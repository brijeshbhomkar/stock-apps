package com.algo.trading.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algo.trading.entities.Retracement;

@Repository
public interface RetracementRepository extends JpaRepository<Retracement, Long> {

}
