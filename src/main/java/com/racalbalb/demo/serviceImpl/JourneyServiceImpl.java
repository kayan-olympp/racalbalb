package com.racalbalb.demo.serviceImpl;

import com.racalbalb.demo.assembler.JourneyResourceAssembler;
import com.racalbalb.demo.domain.Journey;
import com.racalbalb.demo.domain.JourneyPassenger;
import com.racalbalb.demo.domain.Passenger;
import com.racalbalb.demo.exception.ResourceNotFoundException;
import com.racalbalb.demo.repository.JourneyPassengerRepository;
import com.racalbalb.demo.repository.JourneyRepository;
import com.racalbalb.demo.repository.PassengerRepository;
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
public class JourneyServiceImpl implements JourneyService {

    @Autowired
    private final JourneyRepository journeyRepository;
    private final JourneyResourceAssembler journeyAssembler;

    @Autowired

    private JourneyPassengerRepository journeyPassengerRepository;
    @Autowired

    private PassengerRepository passengerRepository;

    public JourneyServiceImpl(JourneyRepository journeyRepository, JourneyResourceAssembler journeyAssembler) {

        this.journeyRepository = journeyRepository;
        this.journeyAssembler = journeyAssembler;
    }

    @PostMapping("seach")
    public ResponseEntity<Object> getJourneyByCities( @RequestParam(name="from")String from, @RequestParam(name="to")String to) {
        List<Journey> journeys =  journeyRepository.findByFromAndTo(from, to);

        return ResponseEntity.ok(journeys);
    }
    @GetMapping()
    public Resources<Resource<Journey>> all() {
        List<Resource<Journey>> journeys = journeyRepository.findAll().stream()
                .map(journeyAssembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(journeys,
                linkTo(methodOn(JourneyServiceImpl.class).all()).withSelfRel());

    }
    @GetMapping("/journey/{journeyId}")
    public Resource<Journey> one(@PathVariable(name="journeyId")Long journeyId) {
        Journey journey = journeyRepository.findById(journeyId)
                .orElseThrow(() -> new ResourceNotFoundException("Journey " + journeyId + " not found"));
        return journeyAssembler.toResource(journey);
    }
    @PostMapping
    public ResponseEntity<Object> saveJourney(Journey journey){
        ResponseEntity<Object> result;
        Optional<Journey> journeyDB = journeyRepository.findById(journey.getId());
        if ( !journeyDB.isPresent() ) {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                    "/{id}").buildAndExpand(journey.getId()).toUri();

            result = ResponseEntity.created(location).build();
        } else {
            result = ResponseEntity.ok().build();

        }
        journeyRepository.save(journey);
        return result;
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
    @Transactional(readOnly = true)
    public ResponseEntity<Object> deleteJourney(@PathVariable(name="journeyId")Long journeyId){

        ResponseEntity<Object> result;
        try {
            journeyPassengerRepository.deleteByJourneyId(journeyId);
            journeyRepository.deleteById(journeyId);
            result = ResponseEntity.accepted().build();
        } catch (Exception e) {
            result = ResponseEntity.notFound().build();
        }
        return result;
    }

    @PutMapping("/{journeyId}")
    public ResponseEntity<Object> updateJourney(@RequestBody Journey journey,
                                                  @PathVariable(name="journeyId")Long journeyId){

        ResponseEntity<Object> result;

        Optional<Journey> pas = journeyRepository.findById(journeyId);

        if (!pas.isPresent())
            result = ResponseEntity.notFound().build();
        else {
            journey.setId(journeyId);
            journeyRepository.save(journey);
            result = ResponseEntity.ok().build();
        }

        return result;

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
    public ResponseEntity<Object> addPassenger(@PathVariable(name="passengerId")Long passengerId, @PathVariable(name="journeyId")Long journeyId) {
        JourneyPassenger jp = new JourneyPassenger(journeyId,passengerId);
        journeyPassengerRepository.save(jp);
        ResponseEntity<Object> result;
        // Check if journey and passenger exist
        Optional<Journey> journeyDB = journeyRepository.findById(journeyId);
        Optional<Passenger> passengerDB = passengerRepository.findById(passengerId);
        boolean exist = journeyPassengerRepository.existsJourneyPassengerByJourneyIdAndAndPassengerId(journeyId,passengerId);
        if ( journeyDB.isPresent() && passengerDB.isPresent()  ) {
            // check passenger isn't already part of the journey
            if (!exist) {
                URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                        "/{journeyId}/passengers/{passengerId}").buildAndExpand(journeyId,passengerId).toUri();
                // return 201
                result = ResponseEntity.created(location).build();
            } else {
                result = ResponseEntity.badRequest().body("Passenger already here.");
            }
        } else {
            result = ResponseEntity.badRequest().body("journey " + journeyId + " or/and "
                    + "passenger " + passengerId + " doesn't exist.");

        }
        return result;
    }

    /**
     *
     * @param passengerId
     * @param journeyId
     * Response with status 201 if deleted
     * Response with status 404 on error
     */
    @DeleteMapping("/{journeyId}/passengers/{passengerId}")
    public ResponseEntity<Object> removePassenger(@PathVariable(name="passengerId")Long passengerId, @PathVariable(name="journeyId")Long journeyId) {
        ResponseEntity<Object> result;
        try {
            JourneyPassenger jp = new JourneyPassenger(journeyId,passengerId);
            journeyPassengerRepository.delete(jp);
            result = ResponseEntity.accepted().build();
        } catch (Exception e) {
            result = ResponseEntity.notFound().build();
        }
        return result;
    }


}

