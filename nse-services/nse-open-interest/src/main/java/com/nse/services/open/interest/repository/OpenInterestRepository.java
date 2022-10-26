package com.nse.services.open.interest.repository;

import com.nse.services.open.interest.model.OpenInterestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpenInterestRepository extends JpaRepository<OpenInterestEntity, Long> {
}
