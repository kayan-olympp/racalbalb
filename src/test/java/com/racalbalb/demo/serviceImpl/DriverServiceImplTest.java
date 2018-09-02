package com.racalbalb.demo.serviceImpl;

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

import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)

public class DriverServiceImplTest {

    @InjectMocks
    private DriverServiceImpl driverServiceImpl;


    @Mock
    private DriverRepository driverRepository;

    @Before
    public void before() {

    }
    @Test
    public void testSaveDriver() throws Exception {
        Driver driver = new Driver(1L,"aa", "bb");
        Driver mockDriver = new Driver(1L,"aa", "bb");
        Mockito.when(driverRepository.save(any(Driver.class))).thenReturn(mockDriver);
        Driver res = driverRepository.save(driver);
        List<Driver> lstDrivers = driverRepository.findAll();
        Assert.assertTrue(res.getId() == 1 );
    }
    @Test
    public void testGetDriverById() throws Exception {
        Driver driver = new Driver(1L,"aa", "bb");
        Driver mockDriver = new Driver(1L,"aa", "bb");
        List<Driver> mockDrivers = new ArrayList<>();
        mockDrivers.add(mockDriver);
        Mockito.when(driverRepository.getOne(1L)).thenReturn(mockDriver);
        Driver res = driverRepository.getOne(1L);
        Assert.assertTrue(res.getId() == mockDriver.getId());
    }
    @Test
    public void testDeleteDriverById() throws Exception {
        Driver driver = new Driver(1L,"aa", "bb");
        Driver mockDriver = new Driver(1L,"aa", "bb");
        List<Driver> mockDrivers = new ArrayList<>();
        mockDrivers.add(mockDriver);
        Mockito.when(driverRepository.getOne(1L)).thenReturn(null);
        driverRepository.deleteById(1L);
        Driver res = driverRepository.getOne(1L);
        Assert.assertTrue(res == null);
    }
}
