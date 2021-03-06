package com.stocks.oi.mongo.repository;

import com.stocks.oi.document.DailyParticipantVolumeDocumentWrapper;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyParticipantVolumeRepository extends MongoRepository<DailyParticipantVolumeDocumentWrapper, String> {

}
