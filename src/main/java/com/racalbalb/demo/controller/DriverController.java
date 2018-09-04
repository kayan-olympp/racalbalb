package com.racalbalb.demo.controller;

import com.racalbalb.demo.assembler.DriverResourceAssembler;
import com.racalbalb.demo.domain.Driver;
import com.racalbalb.demo.domain.Journey;
import com.racalbalb.demo.repository.DriverRepository;
import com.racalbalb.demo.repository.JourneyPassengerRepository;
import com.racalbalb.demo.repository.JourneyRepository;
import com.racalbalb.demo.service.DriverService;
import com.racalbalb.demo.util.ResourceNotFoundException;
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

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    @Autowired
    private final DriverResourceAssembler driverAssembler;
    @Autowired
    private final DriverService driverService;

    public DriverController(DriverService driverService, DriverResourceAssembler driverAssembler) {

        this.driverService = driverService;
        this.driverAssembler = driverAssembler;
    }

    @GetMapping()
    public List<Driver> all() {
        return driverService.all();
    }

    @GetMapping("/drivers/{driverId}")
    public Driver one(@PathVariable(name="driverId")Long driverId) {
        return driverService.one(driverId);
    }

    // create
    @PostMapping
    public Driver saveDriver(Driver driver){
        return driverService.saveDriver(driver);
    }

    @DeleteMapping("/{driverId}")
    public void deleteDriver(@PathVariable(name="driverId")Long driverId){
        driverService.deleteDriver(driverId);
    }
    @PutMapping("/{driverId}")
    public Driver updateDriver(
            @RequestBody Driver driver,
            @PathVariable(name="driverId")Long driverId){

        return driverService.updateDriver(driver, driverId);
    }
}
