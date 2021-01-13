package com.stocks.oi.mongo.repository;

import com.stocks.oi.document.DailyParticipantOpenInterestDocumentWrapper;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface DailyParticipantOpenInterestRepository extends MongoRepository<DailyParticipantOpenInterestDocumentWrapper, String> {

}
