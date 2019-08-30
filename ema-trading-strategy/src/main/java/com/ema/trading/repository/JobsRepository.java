package com.ema.trading.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ema.trading.model.JobDetails;

@Repository
public interface JobsRepository extends JpaRepository<JobDetails, Long> {

}
