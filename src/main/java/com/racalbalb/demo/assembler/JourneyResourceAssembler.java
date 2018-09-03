package com.racalbalb.demo.assembler;

import com.racalbalb.demo.domain.Journey;
import com.racalbalb.demo.serviceImpl.JourneyServiceImpl;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class JourneyResourceAssembler implements ResourceAssembler<Journey, Resource<Journey>> {
    @Override
    public Resource<Journey> toResource(Journey journey) {

        return new Resource<>(journey,
                linkTo(methodOn(JourneyServiceImpl.class).one(journey.getId())).withSelfRel(),
                linkTo(methodOn(JourneyServiceImpl.class).all()).withRel("journeys"));
    }
}
