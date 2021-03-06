package com.racalbalb.demo.repository;

import com.racalbalb.demo.domain.Journey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JourneyRepository extends JpaRepository<Journey,Long> {
    List<Journey> findByFromAndTo(String From, String To);
    void deleteByDriverId(Long driverId);
    List<Journey> findDistinctJourneyIdByDriverId(Long driverId);
}
