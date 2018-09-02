Feature: API BDDs

  @FirstScenario
  Scenario: Receive single arrival
    Given Drivers rest endpoint is up
    When Driver gets one driver by id 1
    Then Returned JSON object is not null
  @LastScenario
  Scenario: Receive single user
    Given Passengers rest endpoint is up
    When Passenger gets one passenger by id 1
    Then Returned JSON object is not null