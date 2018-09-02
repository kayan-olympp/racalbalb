package com.racalbalb.demo.serviceImpl;

import com.racalbalb.demo.domain.Journey;
import com.racalbalb.demo.repository.JourneyRepository;
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
public class JourneyServiceImplTest {
    @InjectMocks
    private JourneyServiceImpl journeyServiceImpl;

    @Mock
    private JourneyRepository journeyRepository;

    @Before
    public void before() {

    }

    @Test
    public void testSaveJourney() {
        Journey journey = new Journey(1L,"aa", "bb");
        Journey mockJourney = new Journey(1L,"aa", "bb");
        Mockito.when(journeyRepository.save(isA(Journey.class))).thenReturn(mockJourney);
        Journey res = journeyRepository.save(journey);
        Assert.assertEquals(res.getId(), mockJourney.getId() );
    }
    @Test
    public void testGetDriverById() {
        Journey mockJourney = new Journey(1L,"aa", "bb");
        List<Journey> mockJourneys = new ArrayList<>();
        mockJourneys.add(mockJourney);
        Mockito.when(journeyRepository.getOne(1L)).thenReturn(mockJourney);
        Journey res = journeyRepository.getOne(1L);
        Assert.assertEquals(res.getId(), mockJourney.getId());
    }
    @Test
    public void testDeleteDriverById() {
        Journey mockJourney = new Journey(1L,"aa", "bb");
        List<Journey> mockJourneys = new ArrayList<>();
        mockJourneys.add(mockJourney);
        Mockito.when(journeyRepository.getOne(1L)).thenReturn(null);
        journeyRepository.deleteById(1L);
        Journey res = journeyRepository.getOne(1L);
        Assert.assertNull(res);
    }
}

