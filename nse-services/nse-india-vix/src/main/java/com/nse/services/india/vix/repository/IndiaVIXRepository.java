package com.nse.services.india.vix.repository;

import com.nse.services.india.vix.model.IndiaVix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndiaVIXRepository extends JpaRepository<IndiaVix, Long> {
}
