package com.racalbalb.demo.serviceImpl;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;


import com.racalbalb.demo.assembler.DriverResourceAssembler;
import com.racalbalb.demo.domain.Driver;
import com.racalbalb.demo.domain.Journey;
import com.racalbalb.demo.exception.ResourceNotFoundException;
import com.racalbalb.demo.repository.DriverRepository;
import com.racalbalb.demo.repository.JourneyPassengerRepository;
import com.racalbalb.demo.repository.JourneyRepository;
import com.racalbalb.demo.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
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

    @Autowired
    private final DriverRepository driverRepository;
    private final DriverResourceAssembler driverAssembler;

    @Autowired
    private JourneyRepository journeyRepository;
    @Autowired
    private JourneyPassengerRepository journeyPassengerRepository;

    public DriverServiceImpl(DriverRepository driverRepository, DriverResourceAssembler driverAssembler) {

        this.driverRepository = driverRepository;
        this.driverAssembler = driverAssembler;
    }

    @GetMapping()
    public Resources<Resource<Driver>> all() {
        List<Resource<Driver>> drivers = driverRepository.findAll().stream()
                .map(driverAssembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(drivers,
                linkTo(methodOn(DriverServiceImpl.class).all()).withSelfRel());
    }
    @GetMapping("/drivers/{driverId}")
    public Resource<Driver> one(@PathVariable(name="driverId")Long driverId) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver " + driverId + "not found"));
        return driverAssembler.toResource(driver);
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
