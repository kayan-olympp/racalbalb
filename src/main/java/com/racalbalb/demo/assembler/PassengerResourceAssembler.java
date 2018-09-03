package com.racalbalb.demo.assembler;

import com.racalbalb.demo.domain.Passenger;
import com.racalbalb.demo.serviceImpl.PassengerServiceImpl;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class PassengerResourceAssembler implements ResourceAssembler<Passenger, Resource<Passenger>> {
    @Override
    public Resource<Passenger> toResource(Passenger passenger) {

        return new Resource<>(passenger,
                linkTo(methodOn(PassengerServiceImpl.class).one(passenger.getId())).withSelfRel(),
                linkTo(methodOn(PassengerServiceImpl.class).all()).withRel("passengers"));
    }
}
