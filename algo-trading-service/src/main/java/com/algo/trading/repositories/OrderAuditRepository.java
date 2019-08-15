package com.algo.trading.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algo.trading.entities.OrderAudit;

@Repository
public interface OrderAuditRepository extends JpaRepository<OrderAudit, Long> {

}
