package com.nse.services.top.gainers.repository;

import com.common.exception.ApplicationException;
import com.nse.services.top.gainers.model.TopGainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TopGainerRepository extends JpaRepository<TopGainer, Long> {

    List<TopGainer> findByDate(final Date date) throws ApplicationException;

    Optional<TopGainer> findByNseSymbol(final String symbol) throws ApplicationException;

}
