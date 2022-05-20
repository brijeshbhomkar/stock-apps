package com.nse.services.volume.gainer.repository;

import com.nse.services.volume.gainer.model.VolumeGainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolumeGainerRepository extends JpaRepository<VolumeGainer, Long> {
}
