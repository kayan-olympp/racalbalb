package com.racalbalb.demo.service;

import com.racalbalb.demo.domain.Passenger;
import org.springframework.http.ResponseEntity;

public interface PassengerService {
    /**
     * Get all passengers from DB
     * @return
     */
    public ResponseEntity<Object> getPassengers();

    /**
     * Get passenger by passengerId from DB
     * @param passengerId
     * @return
     */
    public ResponseEntity<Object> getPassenger(Long passengerId);

    /**
     * Create or update a passenger
     * @param passenger
     * @return
     */
    public ResponseEntity<Object> savePassenger(Passenger passenger);

    /**
     * Delete passenger by passengerId
     * @param passengerId
     * @return
     */
    public ResponseEntity<Object> deletePassenger(Long passengerId);

    /**
     * Update passengerId by new passenger passenger
     * @param passenger
     * @param passengerId
     * @return
     */
    public ResponseEntity<Object> updatePassenger(Passenger passenger, Long passengerId);
}
