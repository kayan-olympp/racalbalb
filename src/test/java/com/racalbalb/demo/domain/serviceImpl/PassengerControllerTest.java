package com.racalbalb.demo.domain.serviceImpl;

import com.racalbalb.demo.controller.PassengerController;
import com.racalbalb.demo.domain.Passenger;
import com.racalbalb.demo.repository.PassengerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.isA;

@RunWith(MockitoJUnitRunner.class)
public class PassengerControllerTest {

    @InjectMocks
    private PassengerController passengerController;

    @Mock
    private PassengerRepository passengerRepository;

    @Before
    public void before() {

    }

    @Test
    public void testSaveDriver() {
        Passenger passenger = new Passenger(1L,"aa", "bb");
        Passenger mockPassenger = new Passenger(1L,"aa", "bb");
        Mockito.when(passengerRepository.save(isA(Passenger.class))).thenReturn(mockPassenger);
        Passenger res = passengerRepository.save(passenger);
        Assert.assertEquals(res.getId(), mockPassenger.getId() );
    }
    @Test
    public void testGetDriverById() {
        Passenger mockPassenger = new Passenger(1L,"aa", "bb");
        List<Passenger> mockPassengers = new ArrayList<>();
        mockPassengers.add(mockPassenger);
        Mockito.when(passengerRepository.getOne(1L)).thenReturn(mockPassenger);
        Passenger res = passengerRepository.getOne(1L);
        Assert.assertEquals(res.getId(), mockPassenger.getId());
    }
    @Test
    public void testDeleteDriverById() {
        Passenger mockPassenger = new Passenger(1L,"aa", "bb");
        List<Passenger> mockPassengers = new ArrayList<>();
        mockPassengers.add(mockPassenger);
        Mockito.when(passengerRepository.getOne(1L)).thenReturn(null);
        passengerRepository.deleteById(1L);
        Passenger res = passengerRepository.getOne(1L);
        Assert.assertNull(res);
    }
}
