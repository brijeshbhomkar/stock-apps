package com.stocks.oi.mongo.repository;

import com.stocks.oi.document.OpenInterestChainDocumentWrapper;
import com.stocks.oi.response.oi.chain.OpenInterestChainJson;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface IndicesOpenInterestRepository extends MongoRepository<OpenInterestChainDocumentWrapper, String> {

    @Query("{ 'expiryDate' : ?0 }")
    public List<OpenInterestChainJson> findByExpiryDate(final String expiryDate);
}
