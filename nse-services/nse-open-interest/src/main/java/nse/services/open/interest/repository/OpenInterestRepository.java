package nse.services.open.interest.repository;

import nse.services.open.interest.model.OpenInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpenInterestRepository extends JpaRepository<OpenInterest, Long> {
}
