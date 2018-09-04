package com.racalbalb.demo.service;

import com.racalbalb.demo.domain.Driver;
import com.racalbalb.demo.util.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

// Business logic abstractions, this layer has no idea how to communicate with datasource
public interface DriverService {
    /**
     * Get all drivers from DB
     * @return
     */
    List<Driver> all();

    /**
     * Get driver by driverId from DB
     * @param driverId
     * @return
     */
    Driver one(Long driverId);

    /**
     * Create or update a driver
     * @param driver
     * @return
     */
    Driver saveDriver(Driver driver);

    /**
     * Delete driver by driverId
     * @param driverId
     * @return
     */
    @Transactional
    void deleteDriver(Long driverId) throws ResourceNotFoundException;

    /**
     * Update driverId by new driver driver
     * @param driver
     * @param driveId
     * @return
     */
    Driver updateDriver(Driver driver, Long driveId);
}
