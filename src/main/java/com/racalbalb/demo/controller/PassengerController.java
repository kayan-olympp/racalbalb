package com.racalbalb.demo.controller;

import com.racalbalb.demo.assembler.PassengerResourceAssembler;
import com.racalbalb.demo.domain.Passenger;
import com.racalbalb.demo.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    private final PassengerService passengerService;
    private final PassengerResourceAssembler passengerAssembler;

    public PassengerController(PassengerService passengerService, PassengerResourceAssembler passengerAssembler) {

        this.passengerService = passengerService;
        this.passengerAssembler = passengerAssembler;
    }

    @GetMapping()
    public List<Passenger> all() {
        return passengerService.all();
    }

    @GetMapping("/{passengerId}")
    public Passenger one(@PathVariable(name="passengerId")Long passengerId) {
        return passengerService.one(passengerId);
    }

    // create
    @PostMapping
    public Passenger savePassenger(Passenger passenger){
       return passengerService.savePassenger(passenger);
    }
    @DeleteMapping("/{passengerId}")
    public void deletePassenger(@PathVariable(name="passengerId")Long passengerId){
        passengerService.deletePassenger(passengerId);

    }
    @PutMapping("/{passengerId}")
    public Passenger updatePassenger(
            @RequestBody Passenger passenger,
            @PathVariable(name="passengerId")Long passengerId){

        return passengerService.updatePassenger(passenger,passengerId);

    }
}
