package com.racalbalb.demo.service;

import com.racalbalb.demo.domain.Driver;
import com.racalbalb.demo.util.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

// Business logic abstractions, this layer has no idea how to communicate with datasource
public interface DriverService {
    /**
     * @return all drivers from DB
     */
    List<Driver> all();

    /**
     * @param driverId driver ID
     * @return driver with id driver
     */
    Driver one(Long driverId);

    /**
     * @param driver driver
     * @return created driver driver
     */
    Driver saveDriver(Driver driver);

    /**
     * Delete driver by driverId
     * @param driverId driverId
     */
    @Transactional
    void deleteDriver(Long driverId) throws ResourceNotFoundException;

    /**
     * @param driver driver
     * @param driveId driverId
     * @return updated driver
     */
    Driver updateDriver(Driver driver, Long driveId);
}
