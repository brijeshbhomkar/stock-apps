package com.common.data.repository;

import com.common.data.model.OpenInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpenInterestRepository extends JpaRepository<OpenInterest, Long> {
}
