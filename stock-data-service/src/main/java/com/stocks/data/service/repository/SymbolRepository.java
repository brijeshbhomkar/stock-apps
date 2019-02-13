package com.stocks.data.service.repository;

import com.stocks.data.service.entity.Symbol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The symbol repository.
 */
@Repository
public interface SymbolRepository extends JpaRepository<Symbol, Long> {
}
