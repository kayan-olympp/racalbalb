package com.racalbalb.demo.service;

import com.racalbalb.demo.domain.Journey;
import com.racalbalb.demo.util.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface JourneyService {
    /**
     * @return all journeys from DB
     */
    List<Journey> all();

    /**
     * Get journey by journeyId from DB
     * @param journeyId journey ID
     * @return journey with journeyId
     */
    Journey one(Long journeyId);

    /**
     * Create or update a journey
     * @param journey journey
     * @return created journey journey
     */
    Journey saveJourney(Journey journey);

    /**
     * Delete journey by journeyId
     * @param journeyId journey ID
     */
    @Transactional
    void deleteJourney(Long journeyId);

    /**
     * Remove passenger with passengerId from journey with journeyId
     * @param journeyId journey ID
     * @param passengerId passenger ID
     */
    @Transactional(readOnly = true)
    void deleteJourneyPassenger(Long journeyId, Long passengerId) throws ResourceNotFoundException;

    /**
     * Update journeyId by new passenger journey
     * @param journey journey
     * @param journeyId journey ID
     * @return updated journey
     */
    Journey updateJourney(Journey journey, Long journeyId);

    /**
     * Add passenger with passengerId to journey with journeyId
     * @param journeyId journey ID
     * @param passengerId passenger ID
     * @return journey
     */
    Journey addPassenger(Long journeyId, Long passengerId);

    /**
     * @param driverId driver ID
     * @return all journey of driver ID
     */
    List<Journey> getJourneyByDriver(Long driverId);

    /**
     * Get journey from from to to
     * @param from : departure
     * @param to : destination
     * @return list of journey from depature to destination
     *
     */
    List<Journey> getJourneyByCities(String from, String to);
}
