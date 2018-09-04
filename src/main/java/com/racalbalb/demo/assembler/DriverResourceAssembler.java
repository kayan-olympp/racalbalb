package com.racalbalb.demo.assembler;

import com.racalbalb.demo.controller.DriverController;
import com.racalbalb.demo.domain.Driver;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class DriverResourceAssembler implements ResourceAssembler<Driver, Resource<Driver>> {
    @Override
    public Resource<Driver> toResource(Driver driver) {
       /* List<Resource<Driver>> drivers = driverRepository.findAll().stream()
                .map(driverAssembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(drivers,
                linkTo(methodOn(DriverController.class).all()).withSelfRel());
        return new Resource<>(driver,
                ControllerLinkBuilder.linkTo(methodOn(DriverController.class).one(driver.getId())).withSelfRel(),
                linkTo(methodOn(DriverController.class).all()).withRel("drivers"));*/
    return null;
    }
}
