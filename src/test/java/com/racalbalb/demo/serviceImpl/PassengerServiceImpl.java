package com.racalbalb.demo.serviceImpl;

import com.racalbalb.demo.domain.Passenger;
import com.racalbalb.demo.repository.PassengerRepository;
import com.racalbalb.demo.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/passengers")
public class PassengerServiceImpl implements PassengerService {
    /**
     * TODO : Do it better for error
     */

    @Autowired
    private PassengerRepository passengerRepository;

    public void setPassengerRepository(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }
    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<Object> getPassengers() {
        List<Passenger> passengers = passengerRepository.findAll();
        return ResponseEntity.ok(passengers);
    }

    @RequestMapping(value="/{passengerId}", method= RequestMethod.GET)
    public ResponseEntity<Object> getPassenger(@PathVariable(name="passengerId")Long passengerId) {
        ResponseEntity<Object> result;
        Optional<Passenger> passenger =  passengerRepository.findById(passengerId);
        if (!passenger.isPresent())
            result = ResponseEntity.notFound().build();
        else
            result = ResponseEntity.ok(passenger.get());
        return result;
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
    public ResponseEntity<Object> deletePassenger(@PathVariable(name="passengerId")Long passengerId){

        ResponseEntity<Object> result;
        try {
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
