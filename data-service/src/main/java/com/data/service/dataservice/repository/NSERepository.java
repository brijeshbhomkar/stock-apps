package com.data.service.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.service.dataservice.entity.Stocks;

public interface NSERepository extends JpaRepository<Stocks, Long> {
}
