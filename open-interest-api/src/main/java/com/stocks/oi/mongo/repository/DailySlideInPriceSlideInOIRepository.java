package com.stocks.oi.mongo.repository;

import com.stocks.oi.document.DailySlideInPriceSlideInOIDocumentWrapper;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailySlideInPriceSlideInOIRepository extends MongoRepository<DailySlideInPriceSlideInOIDocumentWrapper, String> {
}
