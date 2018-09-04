package com.racalbalb.demo.controller;

import com.racalbalb.demo.assembler.DriverResourceAssembler;
import com.racalbalb.demo.domain.Driver;
import com.racalbalb.demo.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    private final DriverResourceAssembler driverAssembler;
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
