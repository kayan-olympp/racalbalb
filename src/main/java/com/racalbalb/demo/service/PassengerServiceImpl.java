package com.racalbalb.demo.service;

import com.racalbalb.demo.domain.Driver;
import com.racalbalb.demo.domain.Journey;
import com.racalbalb.demo.domain.Passenger;
import com.racalbalb.demo.repository.DriverRepository;
import com.racalbalb.demo.repository.JourneyPassengerRepository;
import com.racalbalb.demo.repository.JourneyRepository;
import com.racalbalb.demo.repository.PassengerRepository;
import com.racalbalb.demo.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PassengerServiceImpl implements PassengerService {
    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private JourneyPassengerRepository journeyPassengerRepository;

    @Override
    public List<Passenger> all() {
        return passengerRepository.findAll();
    }

    @Override
    public Passenger one(Long passengerId) {
        return passengerRepository.findById(passengerId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver " + passengerId + " not found"));
    }

    @Override
    public Passenger savePassenger(Passenger passenger) throws ResourceNotFoundException {
        return passengerRepository.save(passenger);
    }

    @Override
    @Transactional
    public void deletePassenger(Long passengerId) throws ResourceNotFoundException {
        journeyPassengerRepository.deleteByPassengerId(passengerId);
        passengerRepository.deleteById(passengerId);

    }

    @Override
    public Passenger updatePassenger(Passenger passenger, Long passengerId) {
        passenger.setId(passengerId);
        return passengerRepository.save(passenger);
    }
}
