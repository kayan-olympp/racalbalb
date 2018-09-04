package com.racalbalb.demo.service;

import com.racalbalb.demo.domain.Driver;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
// Business logic abstractions, this layer has no idea how to communicate with datasource
public interface DriverService {
    /**
     * Get all drivers from DB
     * @return
     */
    Resources<Resource<Driver>> all();

    /**
     * Get driver by driverId from DB
     * @param driverId
     * @return
     */
    Resource<Driver> one(Long driverId);

    /**
     * Create or update a driver
     * @param driver
     * @return
     */
    ResponseEntity<Object> saveDriver(Driver driver);

    /**
     * Delete driver by driverId
     * @param driverId
     * @return
     */
    ResponseEntity<Object> deleteDriver(Long driverId);

    /**
     * Update driverId by new driver driver
     * @param driver
     * @param driveId
     * @return
     */
    public ResponseEntity<Object> updateDriver(Driver driver, Long driveId);
}
