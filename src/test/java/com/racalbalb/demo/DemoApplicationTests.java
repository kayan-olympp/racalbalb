package com.racalbalb.demo;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:test.feature")
public class DemoApplicationTests {


	@Test
	public void contextLoads() {

	}

}
