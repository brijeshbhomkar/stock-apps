package com.data.service.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.data.service.dataservice.entity.Symbol;

@Repository
public interface SymbolRepository extends JpaRepository<Symbol, Long>, StockSymbolRepository {

}
