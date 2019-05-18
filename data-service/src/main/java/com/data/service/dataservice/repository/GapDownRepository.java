package com.data.service.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.service.dataservice.entity.GapDownStock;

public interface GapDownRepository extends JpaRepository<GapDownStock, Long> {

}
