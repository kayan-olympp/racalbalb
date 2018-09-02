package com.racalbalb.demo.service;

import com.racalbalb.demo.domain.Driver;
import org.springframework.http.ResponseEntity;

public interface DriverService {
    /**
     * Get all drivers from DB
     * @return
     */
    public ResponseEntity<Object> getDrivers();

    /**
     * Get driver by driverId from DB
     * @param driverId
     * @return
     */
    public ResponseEntity<Object> getDriver(Long driverId);

    /**
     * Create or update a driver
     * @param driver
     * @return
     */
    public ResponseEntity<Object> saveDriver(Driver driver);

    /**
     * Delete driver by driverId
     * @param driverId
     * @return
     */
    public ResponseEntity<Object> deleteDriver(Long driverId);

    /**
     * Update driverId by new driver driver
     * @param driver
     * @param driveId
     * @return
     */
    public ResponseEntity<Object> updateDriver(Driver driver, Long driveId);
}
