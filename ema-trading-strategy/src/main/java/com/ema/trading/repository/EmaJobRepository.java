package com.ema.trading.repository;

import com.ema.trading.model.EmaJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmaJobRepository extends JpaRepository<EmaJob, Long> {
}
