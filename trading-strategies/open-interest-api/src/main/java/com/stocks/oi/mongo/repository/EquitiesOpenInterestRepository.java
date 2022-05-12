package com.stocks.oi.mongo.repository;

import com.stocks.oi.document.OpenInterestChainDocumentWrapper;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquitiesOpenInterestRepository extends MongoRepository<OpenInterestChainDocumentWrapper, String> {
}
