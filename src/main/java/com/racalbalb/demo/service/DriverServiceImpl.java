package com.racalbalb.demo.service;

import com.racalbalb.demo.controller.DriverController;
import com.racalbalb.demo.domain.Driver;
import com.racalbalb.demo.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class DriverServiceImpl implements DriverService {
    @Autowired
    private DriverRepository driverRepository;
    @Override
    public Resources<Resource<Driver>> all() {
        List<Resource<Driver>> drivers = driverRepository.findAll().stream()
                .map(driverAssembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(drivers,
                linkTo(methodOn(DriverController.class).all()).withSelfRel());
    }

    @Override
    public Resource<Driver> one(Long driverId) {
        return null;
    }

    @Override
    public ResponseEntity<Object> saveDriver(Driver driver) {
        return null;
    }

    @Override
    public ResponseEntity<Object> deleteDriver(Long driverId) {
        return null;
    }

    @Override
    public ResponseEntity<Object> updateDriver(Driver driver, Long driveId) {
        return null;
    }
}
