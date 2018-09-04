package com.racalbalb.demo.controller;

import com.racalbalb.demo.assembler.JourneyResourceAssembler;
import com.racalbalb.demo.domain.Journey;
import com.racalbalb.demo.domain.JourneyPassenger;
import com.racalbalb.demo.domain.Passenger;
import com.racalbalb.demo.util.ResourceNotFoundException;
import com.racalbalb.demo.repository.JourneyPassengerRepository;
import com.racalbalb.demo.repository.PassengerRepository;
import com.racalbalb.demo.repository.JourneyRepository;
import com.racalbalb.demo.service.JourneyService;
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
@RequestMapping("/api/journeys")
public class JourneyController {

    @Autowired
    private final JourneyService journeyService;
    private final JourneyResourceAssembler journeyAssembler;

    public JourneyController(JourneyService journeyService, JourneyResourceAssembler journeyAssembler) {

        this.journeyService = journeyService;
        this.journeyAssembler = journeyAssembler;
    }

    @PostMapping("/seach")
    public List<Journey>  getJourneyByCities( @RequestParam(name="from")String from, @RequestParam(name="to")String to) {
        return journeyService.getJourneyByCities(from, to);
    }

    @GetMapping()
    public List<Journey> all() {
        return journeyService.all();
    }

    @GetMapping("/journey/{journeyId}")
    public Journey one(@PathVariable(name="journeyId")Long journeyId) {
        return journeyService.one(journeyId);
    }

    @PostMapping
    public Journey saveJourney(Journey journey){
        return journeyService.saveJourney(journey);
    }

    /**
     * Remove a journey
     * Should remove all passenger to this journey
     * @param journeyId
     * @return
     * Response with status 201 if deleted
     * Response with status 404 on error
     */
    @DeleteMapping("/{journeyId}")
    public void deleteJourney(@PathVariable(name="journeyId")Long journeyId){
        journeyService.deleteJourney(journeyId);
    }

    @PutMapping("/{journeyId}")
    public Journey updateJourney(
            @RequestBody Journey journey,
            @PathVariable(name="journeyId")Long journeyId){

        return journeyService.saveJourney(journey);


    }

    /**
     * Add a passenger to a journey
     * @param passengerId : passenger identifuer
     * @param journeyId : journey : identifier
     * @return
     * Response with status 201 if created
     * Response with status 400 (bad request) on error
     */
   @PostMapping("/{journeyId}/passengers/{passengerId}")
    public Journey addPassenger(
            @PathVariable(name="passengerId")Long passengerId,
            @PathVariable(name="journeyId")Long journeyId) {
       return journeyService.addPassenger(journeyId, passengerId);
   }

    /**
     *
     * @param passengerId
     * @param journeyId
     * Response with status 201 if deleted
     * Response with status 404 on error
     */
   @DeleteMapping("/{journeyId}/passengers/{passengerId}")
   public void deleteJourneyPassenger(@PathVariable(name="passengerId")Long passengerId, @PathVariable(name="journeyId")Long journeyId) {
       journeyService.deleteJourneyPassenger(journeyId,passengerId);
    }


}

