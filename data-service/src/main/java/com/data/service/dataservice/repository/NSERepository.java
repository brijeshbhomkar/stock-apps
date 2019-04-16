package com.data.service.dataservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.data.service.dataservice.entity.Stocks;

public interface NSERepository extends JpaRepository<Stocks, Long> {
	
	@Query(name = " select s from stock s where s.price <= :price ", nativeQuery = true)
	List<Stocks> findByPrice(@Param("price") final String price);
}
