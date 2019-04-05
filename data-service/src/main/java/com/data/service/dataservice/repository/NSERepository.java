package com.data.service.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.service.dataservice.entity.Stock;

public interface NSERepository extends JpaRepository<Stock, Long> {
}
