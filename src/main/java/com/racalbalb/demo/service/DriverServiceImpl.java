package com.racalbalb.demo.service;

import com.racalbalb.demo.controller.DriverController;
import com.racalbalb.demo.domain.Driver;
import com.racalbalb.demo.domain.Journey;
import com.racalbalb.demo.repository.DriverRepository;
import com.racalbalb.demo.repository.JourneyPassengerRepository;
import com.racalbalb.demo.repository.JourneyRepository;
import com.racalbalb.demo.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
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
