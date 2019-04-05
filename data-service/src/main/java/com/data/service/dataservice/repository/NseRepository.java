package com.data.service.dataservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.service.dataservice.entity.NiftyFifty;

public interface NseRepository extends JpaRepository<NiftyFifty, Long> {
}
