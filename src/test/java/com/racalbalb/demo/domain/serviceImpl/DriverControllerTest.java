package com.racalbalb.demo.domain.serviceImpl;

import com.racalbalb.demo.controller.DriverController;
import com.racalbalb.demo.domain.Driver;
import com.racalbalb.demo.repository.DriverRepository;
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
public class DriverControllerTest {

    @InjectMocks
    private DriverController driverController;

    @Mock
    private DriverRepository driverRepository;

    @Before
    public void before() {

    }

    @Test
    public void testSaveDriver() {
        Driver driver = new Driver(1L,"aa", "bb");
        Driver mockDriver = new Driver(1L,"aa", "bb");
        Mockito.when(driverRepository.save(isA(Driver.class))).thenReturn(mockDriver);
        Driver res = driverRepository.save(driver);
        Assert.assertEquals(res.getId(), mockDriver.getId());
    }
    @Test
    public void testGetDriverById() {
        Driver mockDriver = new Driver(1L,"aa", "bb");
        List<Driver> mockDrivers = new ArrayList<>();
        mockDrivers.add(mockDriver);
        Mockito.when(driverRepository.getOne(1L)).thenReturn(mockDriver);
        Driver res = driverRepository.getOne(1L);
        Assert.assertEquals(res.getId(), mockDriver.getId());

    }
    @Test
    public void testDeleteDriverById() {
        Driver mockDriver = new Driver(1L,"aa", "bb");
        List<Driver> mockDrivers = new ArrayList<>();
        mockDrivers.add(mockDriver);
        Mockito.when(driverRepository.getOne(1L)).thenReturn(null);
        driverRepository.deleteById(1L);
        Driver res = driverRepository.getOne(1L);
        Assert.assertNull(res);
    }
}
