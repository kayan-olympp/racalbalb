package com.racalbalb.demo.controller;

import com.racalbalb.demo.assembler.JourneyResourceAssembler;
import com.racalbalb.demo.domain.Journey;
import com.racalbalb.demo.service.JourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/journeys")
public class JourneyController {

    private final JourneyService journeyService;
    private final JourneyResourceAssembler journeyAssembler;

    public JourneyController(JourneyService journeyService, JourneyResourceAssembler journeyAssembler) {

        this.journeyService = journeyService;
        this.journeyAssembler = journeyAssembler;
    }

    @PostMapping("/seach")
    public List<Journey> getJourneyByCities( @RequestParam(name="from")String from, @RequestParam(name="to")String to) {
        return journeyService.getJourneyByCities(from, to);
    }
    @GetMapping("/driver/{driverId}")
    public List<Journey> getJourneyByCities( @RequestParam(name="driver")Long driver) {
        return journeyService.getJourneyByDriver(driver);
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
     * @param journeyId journey ID
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
     * @param passengerId passenger ID
     * @param journeyId journey ID
     * Response with status 201 if deleted
     * Response with status 404 on error
     */
   @DeleteMapping("/{journeyId}/passengers/{passengerId}")
   public void deleteJourneyPassenger(@PathVariable(name="passengerId")Long passengerId, @PathVariable(name="journeyId")Long journeyId) {
       journeyService.deleteJourneyPassenger(journeyId,passengerId);
    }


}

