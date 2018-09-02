package com.racalbalb.demo.serviceImpl;

import com.racalbalb.demo.domain.Driver;
import com.racalbalb.demo.domain.Journey;
import com.racalbalb.demo.repository.DriverRepository;
import com.racalbalb.demo.repository.JourneyPassengerRepository;
import com.racalbalb.demo.repository.JourneyRepository;
import com.racalbalb.demo.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/drivers")
public class DriverServiceImpl implements DriverService {
    /**
     * TODO : Do it better for error
     * TODO : Exception when FK on delete
     */

    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private JourneyRepository journeyRepository;
    @Autowired
    private JourneyPassengerRepository journeyPassengerRepository;

    public void setDriverRepository(DriverRepository driverRepository) {

        this.driverRepository = driverRepository;
    }

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<Object> getDrivers() {
        List<Driver> drivers = driverRepository.findAll();
        return ResponseEntity.ok(drivers);
    }

    @RequestMapping(value="/{driverId}", method= RequestMethod.GET)
    public ResponseEntity<Object> getDriver(@PathVariable(name="driverId")Long driverId) {
        ResponseEntity<Object> result;
        Optional<Driver> driver =  driverRepository.findById(driverId);
        if (!driver.isPresent())
            result = ResponseEntity.notFound().build();
        else
            result = ResponseEntity.ok(driver.get());
        return result;
    }
    // create
    @PostMapping
    public ResponseEntity<Object> saveDriver(Driver driver){
        ResponseEntity<Object> result;
        Optional<Driver> pas = driverRepository.findById(driver.getId());
        if ( !pas.isPresent() ) {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                    "/{id}").buildAndExpand(driver.getId()).toUri();

            result = ResponseEntity.created(location).build();
        } else {
            result = ResponseEntity.ok().build();

        }
        driverRepository.save(driver);
        return result;
    }
    @DeleteMapping("/{driverId}")
    @Transactional(readOnly = true)
    public ResponseEntity<Object> deleteDriver(@PathVariable(name="driverId")Long driverId){
        ResponseEntity<Object> result;
        try {
            // get all journey of driver
            List<Journey> journeysOfDriver =journeyRepository.findDistinctJourneyIdByDriverId(driverId);
            List<Long> journeysOfDriverId = journeysOfDriver.stream().map(Journey::getId).collect(Collectors.toList());
            // deleta all passenger of a driver's journeys
            journeyPassengerRepository.deleteAllByJourneyIdIn(journeysOfDriverId);
            // then delete all journey of driver
            journeyRepository.deleteByDriverId(driverId);
            // then delete all journey of driver
            driverRepository.deleteById(driverId);
            result = ResponseEntity.accepted().build();
        } catch (Exception e) {
            result = ResponseEntity.notFound().build();
        }
        return result;
    }
    @PutMapping("/{driverId}")
    public ResponseEntity<Object> updateDriver(
            @RequestBody Driver driver,
            @PathVariable(name="driverId")Long driverId){
        ResponseEntity<Object> result;

        Optional<Driver> driverDb = driverRepository.findById(driverId);

        if (!driverDb.isPresent())
            result = ResponseEntity.notFound().build();
        else {
            driver.setId(driverId);
            driverRepository.save(driver);
            result = ResponseEntity.ok().build();
        }

        return result;
    }
}
