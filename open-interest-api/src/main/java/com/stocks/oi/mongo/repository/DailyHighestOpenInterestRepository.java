package com.stocks.oi.mongo.repository;

import com.stocks.oi.document.DailyHighestOpenInterestDocumentWrapper;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyHighestOpenInterestRepository extends MongoRepository<DailyHighestOpenInterestDocumentWrapper, String> {
}
