package com.algo.trading.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algo.trading.entities.OrderJob;

@Repository
public interface OrderJobRepository extends JpaRepository<OrderJob, Long> {

}
