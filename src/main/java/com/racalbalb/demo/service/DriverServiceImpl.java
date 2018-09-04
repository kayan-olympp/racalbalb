package com.racalbalb.demo.service;

import com.racalbalb.demo.domain.Driver;
import com.racalbalb.demo.repository.DriverRepository;
import com.racalbalb.demo.repository.JourneyPassengerRepository;
import com.racalbalb.demo.repository.JourneyRepository;
import com.racalbalb.demo.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
public class DriverServiceImpl implements DriverService {
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private JourneyRepository journeyRepository;
    @Autowired
    private JourneyPassengerRepository journeyPassengerRepository;

    @Override
    public List<Driver> all() {
        return driverRepository.findAll();
    }

    @Override
    public Driver one(Long driverId) {
        return driverRepository.findById(driverId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver " + driverId + " not found"));
    }

    @Override
    public Driver saveDriver(Driver driver) throws ResourceNotFoundException {
        return driverRepository.save(driver);
    }

    @Override
    @Transactional
    public void deleteDriver(Long driverId) throws ResourceNotFoundException {
        // then delete all journey of driver
        journeyRepository.deleteByDriverId(driverId);
        // then delete all journey of driver
        driverRepository.deleteById(driverId);

    }

    @Override
    public Driver updateDriver(Driver driver, Long driveId) {
        driver.setId(driveId);
        return driverRepository.save(driver);
    }
}
