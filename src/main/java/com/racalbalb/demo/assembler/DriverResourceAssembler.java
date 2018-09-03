package com.racalbalb.demo.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.racalbalb.demo.domain.Driver;
import com.racalbalb.demo.serviceImpl.DriverServiceImpl;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
public class DriverResourceAssembler implements ResourceAssembler<Driver, Resource<Driver>> {
    @Override
    public Resource<Driver> toResource(Driver driver) {

        return new Resource<>(driver,
                linkTo(methodOn(DriverServiceImpl.class).one(driver.getId())).withSelfRel(),
                linkTo(methodOn(DriverServiceImpl.class).all()).withRel("drivers"));
    }
}
