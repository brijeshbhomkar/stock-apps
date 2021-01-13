package com.stocks.oi.mongo.repository;

import com.stocks.oi.document.Quote.Quote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterQuoteRepository extends MongoRepository<Quote, String> {
}
