package com.racalbalb.demo.service;

import com.racalbalb.demo.domain.Journey;
import com.racalbalb.demo.util.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface JourneyService {
    /**
     * Get all journeys from DB
     * @return
     */
    List<Journey> all();

    /**
     * Get journey by journeyId from DB
     * @param journeyId
     * @return
     */

    Journey one(Long journeyId);
    /**
     * Create or update a journey
     * @param journey
     * @return
     */

    Journey saveJourney(Journey journey);
    /**
     * Delete journey by journeyId
     * @param journeyId
     * @return
     */
    @Transactional
    void deleteJourney(Long journeyId);

    /**
     * Remove passenger with passengerId from journey with journeyId
     * @param journeyId
     * @param passengerId
     * @return
     */
    @Transactional(readOnly = true)
    void deleteJourneyPassenger(Long journeyId, Long passengerId) throws ResourceNotFoundException;

    /**
     * Update journeyId by new passenger journey
     * @param journey
     * @param journeyId
     * @return
     */
    Journey updateJourney(Journey journey, Long journeyId);

    /**
     * Add passenger with passengerId to journey with journeyId
     * @param journeyId : journey id
     * @param passengerId : passenger id
     * @return
     */
    Journey addPassenger(Long journeyId, Long passengerId);



    /**
     * Get journey from from to to
     * @param from : departure
     * @param to : destination
     * @return list of journey from depature to destination
     *
     */
    List<Journey> getJourneyByCities(String from, String to);
}
