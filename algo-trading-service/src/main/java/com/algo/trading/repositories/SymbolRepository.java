package com.algo.trading.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algo.trading.entities.Symbol;


@Repository
public interface SymbolRepository extends JpaRepository<Symbol, Long>, StockSymbolCrudRepository {

}
