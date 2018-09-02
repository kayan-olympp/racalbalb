package com.racalbalb.demo.service;

import com.racalbalb.demo.domain.Journey;
import org.springframework.http.ResponseEntity;

public interface JourneyService {
    /**
     * Get all journeys from DB
     * @return
     */
    public ResponseEntity<Object> getJourneys();

    /**
     * Get journey by journeyId from DB
     * @param journeyId
     * @return
     */

    public ResponseEntity<Object> getJourney(Long journeyId);
    /**
     * Create or update a journey
     * @param journey
     * @return
     */

    public ResponseEntity<Object> saveJourney(Journey journey);
    /**
     * Delete journey by journeyId
     * @param journeyId
     * @return
     */
    public ResponseEntity<Object> deleteJourney(Long journeyId);

    /**
     * Update journeyId by new passenger journey
     * @param journey
     * @param journeyId
     * @return
     */
    public ResponseEntity<Object> updateJourney(Journey journey, Long journeyId);

    /**
     * Add passenger with passengerId to journey with journeyId
     * @param journeyId
     * @param passengerId
     * @return
     */
    public ResponseEntity<Object> addPassenger(Long journeyId, Long passengerId);

    /**
     * Remove passenger with passengerId from journey with journeyId
     * @param journeyId
     * @param passengerId
     * @return
     */
    public ResponseEntity<Object> removePassenger(Long journeyId, Long passengerId);

    /**
     * Get journey from from to to
     * @param from
     * @param to
     * @return
     */
    public ResponseEntity<Object> getJourneyByCities(String from, String to);
}
