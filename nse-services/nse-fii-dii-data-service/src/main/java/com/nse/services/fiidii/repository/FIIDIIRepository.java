package com.nse.services.fiidii.repository;

import com.nse.services.fiidii.entity.FiiDiiParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FIIDIIRepository extends JpaRepository<FiiDiiParticipantEntity, Long> {

    public List<FiiDiiParticipantEntity> getByCreatedDate(final Date date);
}
