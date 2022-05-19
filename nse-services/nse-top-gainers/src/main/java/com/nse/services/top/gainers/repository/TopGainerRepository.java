package com.nse.services.top.gainers.repository;

import com.nse.services.top.gainers.model.TopGainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopGainerRepository extends JpaRepository<TopGainer, Long> {
}
