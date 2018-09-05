package com.racalbalb.demo.service;

import com.racalbalb.demo.domain.Passenger;
import com.racalbalb.demo.repository.JourneyPassengerRepository;
import com.racalbalb.demo.repository.PassengerRepository;
import com.racalbalb.demo.util.AlreadyExistException;
import com.racalbalb.demo.util.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PassengerServiceImpl implements PassengerService {

    private PassengerRepository passengerRepository;

    private JourneyPassengerRepository journeyPassengerRepository;

    public PassengerServiceImpl (PassengerRepository passengerRepository, JourneyPassengerRepository journeyPassengerRepository) {
        this.passengerRepository = passengerRepository;
        this.journeyPassengerRepository = journeyPassengerRepository;
    }

    @Override
    public List<Passenger> all() {
        return passengerRepository.findAll();
    }

    @Override
    public Passenger one(Long passengerId) throws ResourceNotFoundException {
        return passengerRepository.findById(passengerId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver " + passengerId + " not found"));
    }

    @Override
    public Passenger savePassenger(Passenger passenger) throws AlreadyExistException {
        return passengerRepository.save(passenger);
    }

    @Override
    @Transactional
    public void deletePassenger(Long passengerId) throws ResourceNotFoundException {
        journeyPassengerRepository.deleteByPassengerId(passengerId);
        passengerRepository.deleteById(passengerId);
    }

    @Override
    public Passenger updatePassenger(Passenger passenger, Long passengerId) throws ResourceNotFoundException {
        passenger.setId(passengerId);
        return passengerRepository.save(passenger);
    }
}
