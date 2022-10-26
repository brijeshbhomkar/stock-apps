package com.nse.services.participant.repository;

import com.nse.services.participant.entity.ParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ParticipantRepository extends JpaRepository<ParticipantEntity, Long> {

    public List<ParticipantEntity> getByCreatedDate(final Date date);
}
