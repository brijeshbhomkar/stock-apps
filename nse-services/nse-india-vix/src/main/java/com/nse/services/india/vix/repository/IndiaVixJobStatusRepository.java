package com.nse.services.india.vix.repository;

import com.nse.services.india.vix.model.IndiaVixJobStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndiaVixJobStatusRepository extends JpaRepository<IndiaVixJobStatus, Long> {
}
