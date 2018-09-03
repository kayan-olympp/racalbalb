package com.racalbalb.demo.service;

import com.racalbalb.demo.domain.Passenger;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;

public interface PassengerService {
    /**
     * Get all passengers from DB
     * @return
     */
    Resources<Resource<Passenger>> all();

    /**
     * Get passenger by passengerId from DB
     * @param passengerId
     * @return
     */
    Resource<Passenger> one(Long passengerId);

    /**
     * Create or update a passenger
     * @param passenger
     * @return
     */
    ResponseEntity<Object> savePassenger(Passenger passenger);

    /**
     * Delete passenger by passengerId
     * @param passengerId
     * @return
     */
    ResponseEntity<Object> deletePassenger(Long passengerId);

    /**
     * Update passengerId by new passenger passenger
     * @param passenger
     * @param passengerId
     * @return
     */
    ResponseEntity<Object> updatePassenger(Passenger passenger, Long passengerId);
}
