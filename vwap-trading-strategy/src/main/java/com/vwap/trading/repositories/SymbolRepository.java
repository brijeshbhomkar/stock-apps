package com.vwap.trading.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vwap.trading.models.Symbol;


@Repository
public interface SymbolRepository extends JpaRepository<Symbol, Long>, SymbolCrudRepository {

}
