package com.stocks.oi.mongo.repository;

import com.stocks.oi.document.DailyRiseInPriceRiseInOIDocumentWrapper;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyRiseInPriceRiseInOIRepository extends MongoRepository<DailyRiseInPriceRiseInOIDocumentWrapper, String> {
}
