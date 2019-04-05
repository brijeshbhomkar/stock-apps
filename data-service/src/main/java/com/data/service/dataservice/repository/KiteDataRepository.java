package com.data.service.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.data.service.dataservice.entity.CandleTick;

@Repository
public interface KiteDataRepository extends JpaRepository<CandleTick, Long> {

	@Query("delete from candle c where c.symbol=:symbol")
	public void deleteBySymbolId(@Param("symbol") final String symbol);
}
