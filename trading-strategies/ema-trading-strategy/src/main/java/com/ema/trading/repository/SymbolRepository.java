package com.ema.trading.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ema.trading.model.Symbol;


@Repository
public interface SymbolRepository extends JpaRepository<Symbol, Long>, SymbolCrudRepository {

}
