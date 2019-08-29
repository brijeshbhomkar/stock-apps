package com.vwap.trading.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vwap.trading.models.JobDetails;

@Repository
public interface JobsRepository extends JpaRepository<JobDetails, Long> {

}
