package com.racalbalb.demo.service;

import com.racalbalb.demo.domain.Journey;
import com.racalbalb.demo.domain.JourneyPassenger;
import com.racalbalb.demo.repository.JourneyPassengerRepository;
import com.racalbalb.demo.repository.JourneyRepository;
import com.racalbalb.demo.repository.PassengerRepository;
import com.racalbalb.demo.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Service
public class JourneyServiceImpl implements JourneyService {

    @Autowired
    private JourneyRepository journeyRepository;

    @Autowired
    private JourneyPassengerRepository journeyPassengerRepository;

    @Autowired
    private PassengerRepository passengerRepository;
    @Override
    public List<Journey> getJourneyByCities( String from, String to) {
        return  journeyRepository.findByFromAndTo(from, to);

    }
    @Override
    public List<Journey> all() {
        return journeyRepository.findAll();
    }
    @Override
    public Journey one(Long journeyId) {
        return journeyRepository.findById(journeyId)
                .orElseThrow(() -> new ResourceNotFoundException("Journey " + journeyId + " not found"));
    }
    @Override
    public Journey saveJourney(Journey journey){
        return journeyRepository.save(journey);

    }

    /**
     * Remove a journey
     * Should remove all passenger to this journey
     * @param journeyId
     * Response with status 201 if deleted
     * Response with status 404 on error
     */
    @Override
    @Transactional
    public void deleteJourney(Long journeyId){
        journeyRepository.deleteById(journeyId);
    }
    /**
     *
     * @param passengerId
     * @param journeyId
     * Response with status 201 if deleted
     * Response with status 404 on error
     */
    @Override
    @Transactional
    public void deleteJourneyPassenger (
            Long journeyId,
            Long passengerId
            ) throws ResourceNotFoundException {
        //JourneyPassenger jp = new JourneyPassenger(journeyId,passengerId);
        journeyPassengerRepository.deleteByJourneyIdAndPassengerId(journeyId,passengerId);
        //journeyPassengerRepository.delete(jp);
    }

    @Override
    public Journey updateJourney(
            @RequestBody Journey journey,
            @PathVariable(name="journeyId")Long journeyId){

        journey.setId(journeyId);
        return journeyRepository.save(journey);

    }

    /**
     * Add a passenger to a journey
     * @param passengerId : passenger identifuer
     * @param journeyId : journey : identifier
     * @return
     * Response with status 201 if created
     * Response with status 400 (bad request) on error
     */
    public Journey addPassenger( Long journeyId, Long passengerId ) {
        JourneyPassenger jp = new JourneyPassenger(journeyId,passengerId);
        journeyPassengerRepository.save(jp);
        return journeyRepository.findById(journeyId).orElse(new Journey());
    }

}

