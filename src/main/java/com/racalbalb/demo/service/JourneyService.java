package com.racalbalb.demo.service;

import com.racalbalb.demo.domain.Journey;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;

public interface JourneyService {
    /**
     * Get all journeys from DB
     * @return
     */
    Resources<Resource<Journey>> all();

    /**
     * Get journey by journeyId from DB
     * @param journeyId
     * @return
     */

    Resource<Journey> one(Long journeyId);
    /**
     * Create or update a journey
     * @param journey
     * @return
     */

    ResponseEntity<Object> saveJourney(Journey journey);
    /**
     * Delete journey by journeyId
     * @param journeyId
     * @return
     */
    ResponseEntity<Object> deleteJourney(Long journeyId);

    /**
     * Update journeyId by new passenger journey
     * @param journey
     * @param journeyId
     * @return
     */
    ResponseEntity<Object> updateJourney(Journey journey, Long journeyId);

    /**
     * Add passenger with passengerId to journey with journeyId
     * @param journeyId : journey id
     * @param passengerId : passenger id
     * @return
     */
    ResponseEntity<Object> addPassenger(Long journeyId, Long passengerId);

    /**
     * Remove passenger with passengerId from journey with journeyId
     * @param journeyId
     * @param passengerId
     * @return
     */
    ResponseEntity<Object> removePassenger(Long journeyId, Long passengerId);

    /**
     * Get journey from from to to
     * @param from : departure
     * @param to : destination
     * @return list of journey from depature to destination
     *
     */
    ResponseEntity<Object> getJourneyByCities(String from, String to);
}
