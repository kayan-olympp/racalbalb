package com.racalbalb.demo.serviceImpl;

import com.racalbalb.demo.assembler.PassengerResourceAssembler;
import com.racalbalb.demo.domain.Passenger;
import com.racalbalb.demo.exception.ResourceNotFoundException;
import com.racalbalb.demo.repository.JourneyPassengerRepository;
import com.racalbalb.demo.repository.PassengerRepository;
import com.racalbalb.demo.service.PassengerService;
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
@RequestMapping("/api/passengers")
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private final PassengerRepository passengerRepository;
    private final PassengerResourceAssembler passengerAssembler;
    @Autowired
    private JourneyPassengerRepository journeyPassengerRepository;

    public PassengerServiceImpl(PassengerRepository passengerRepository, PassengerResourceAssembler passengerAssembler) {

        this.passengerRepository = passengerRepository;
        this.passengerAssembler = passengerAssembler;
    }

    @GetMapping()
    public Resources<Resource<Passenger>> all() {
        List<Resource<Passenger>> passengers = passengerRepository.findAll().stream()
                .map(passengerAssembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(passengers,
                linkTo(methodOn(PassengerServiceImpl.class).all()).withSelfRel());
    }

    @GetMapping("/{passengerId}")
    public Resource<Passenger> one(@PathVariable(name="passengerId")Long passengerId) {
        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new ResourceNotFoundException("Passenger " + passengerId + "not found"));
        return passengerAssembler.toResource(passenger);
    }

    // create
    @PostMapping
    public ResponseEntity<Object> savePassenger(Passenger passenger){
        ResponseEntity<Object> result;
        Optional<Passenger> pas = passengerRepository.findById(passenger.getId());
        if ( !pas.isPresent() ) {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                    "/{id}").buildAndExpand(passenger.getId()).toUri();

            result = ResponseEntity.created(location).build();
        } else {
            result = ResponseEntity.ok().build();

        }
        passengerRepository.save(passenger);
        return result;
    }
    @DeleteMapping("/{passengerId}")
    @Transactional(readOnly = true)
    public ResponseEntity<Object> deletePassenger(@PathVariable(name="passengerId")Long passengerId){

        ResponseEntity<Object> result;
        try {

            journeyPassengerRepository.deleteByPassengerId(passengerId);
            passengerRepository.deleteById(passengerId);

            result = ResponseEntity.accepted().build();
        } catch (Exception e) {
            result = ResponseEntity.notFound().build();
        }
        return result;
    }
    @PutMapping("/{passengerId}")
    public ResponseEntity<Object> updatePassenger(
            @RequestBody Passenger passenger,
            @PathVariable(name="passengerId")Long passengerId){

        ResponseEntity<Object> result;

        Optional<Passenger> pas = passengerRepository.findById(passengerId);

        if (!pas.isPresent())
            result = ResponseEntity.notFound().build();
        else {
            passenger.setId(passengerId);
            passengerRepository.save(passenger);
            result = ResponseEntity.ok().build();
        }

        return result;

    }
}
