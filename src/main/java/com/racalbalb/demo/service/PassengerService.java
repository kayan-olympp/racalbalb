package com.racalbalb.demo.service;

import com.racalbalb.demo.domain.Passenger;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PassengerService {
    /**
     * @return all passengers from DB
     */
    List<Passenger> all();

    /**
     * @param passengerId passenger ID
     * @return passenger with id passengerId
     */
    Passenger one(Long passengerId);

    /**
     * @param passenger passenger to create
     * @return created passenger passenger
     */
    Passenger savePassenger(Passenger passenger);

    /**
     * Delete passenger by passengerId
     * @param passengerId passenger ID
     */
    @Transactional(readOnly = true)
    void deletePassenger(Long passengerId);

    /**
     * @param passenger passenger information to change
     * @param passengerId passenger id to update
     * @return updated passenger
     */
   Passenger updatePassenger(Passenger passenger, Long passengerId);
}
