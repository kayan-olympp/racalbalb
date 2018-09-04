package com.racalbalb.demo.service;

import com.racalbalb.demo.domain.Passenger;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PassengerService {
    /**
     * Get all passengers from DB
     * @return
     */
    List<Passenger> all();

    /**
     * Get passenger by passengerId from DB
     * @param passengerId
     * @return
     */
    Passenger one(Long passengerId);

    /**
     * Create or update a passenger
     * @param passenger
     * @return
     */
    Passenger savePassenger(Passenger passenger);

    /**
     * Delete passenger by passengerId
     * @param passengerId
     * @return
     */
    @Transactional(readOnly = true)
    void deletePassenger(Long passengerId);

    /**
     * Update passengerId by new passenger passenger
     * @param passenger
     * @param passengerId
     * @return
     */
   Passenger updatePassenger(Passenger passenger, Long passengerId);
}
