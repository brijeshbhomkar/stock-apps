package com.stocks.oi.mongo.repository;

import com.stocks.oi.document.DailyRiseInPriceSlideInOIDocumentWrapper;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyRiseInPriceSlideInOIRepository extends MongoRepository<DailyRiseInPriceSlideInOIDocumentWrapper, String> {
}
