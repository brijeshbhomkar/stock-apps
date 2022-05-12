package com.stocks.oi.mongo.repository;

import com.stocks.oi.document.DailySlideInPriceRiseInOIDocumentWrapper;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailySlideInPriceRiseInOIRepository extends MongoRepository<DailySlideInPriceRiseInOIDocumentWrapper, String> {
}
