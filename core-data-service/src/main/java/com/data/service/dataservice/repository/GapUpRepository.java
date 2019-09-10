package com.data.service.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.service.dataservice.entity.GapUpStock;

public interface GapUpRepository extends JpaRepository<GapUpStock, String> {

}
