package com.nse.services.sgx.nifty.repository;

import com.nse.services.sgx.nifty.model.SGXNiftyOpenInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SGXNiftyOpenInterestRepository extends JpaRepository<SGXNiftyOpenInterest, Long> {
}
