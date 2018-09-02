package com.racalbalb.demo.repository;

import com.racalbalb.demo.domain.JourneyPassenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JourneyPassengerRepository extends JpaRepository<JourneyPassenger,Long> {
    void deleteAllByJourneyId(Long driverId);
    boolean existsJourneyPassengerByJourneyIdAndAndPassengerId(Long journeyId, Long passengerId);
}
