package com.racalbalb.demo.repository;

import com.racalbalb.demo.domain.JourneyPassenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JourneyPassengerRepository extends JpaRepository<JourneyPassenger, JourneyPassenger> {
    void deleteByJourneyId(Long driverId);
    void deleteByPassengerId(Long passengerId);
    void deleteAllByJourneyIdIn(List<Long> journeyId);
    boolean existsJourneyPassengerByJourneyIdAndAndPassengerId(Long journeyId, Long passengerId);
}
