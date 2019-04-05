package com.data.service.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.data.service.dataservice.entity.Symbol;

@Repository
public interface SymbolRepository extends JpaRepository<Symbol, Long> {

	@Query(value = "select * from stock_data.symbol s where s.symbol = :sym", nativeQuery = true)
	Symbol findSymbolById(@Param("sym") String sym);
}
