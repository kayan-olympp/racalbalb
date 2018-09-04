package com.racalbalb.demo.repository;

import com.racalbalb.demo.domain.JourneyPassenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface JourneyPassengerRepository extends JpaRepository<JourneyPassenger, JourneyPassenger> {
    long deleteByJourneyIdAndPassengerId(Long journeyId, Long passengerId);
    // void deleteByJourneyId(Long journeyId);
    void deleteByPassengerId(Long passengerId);
    // void deleteAllByJourneyIdIn(List<Long> journeyId);
    // boolean existsJourneyPassengerByJourneyIdAndAndPassengerId(Long journeyId, Long passengerId);
}
