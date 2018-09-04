Feature: Features of my service

  Scenario: Empty response
    Given I call GET "http://localhost:8080"
    Then The response status should be 200
    And The response is empty

  Scenario: With data
    Given I call POST "http://localhost:8080/withData" with data:
  """
    test: "test"
  """
    Then The response status should be 200
    And The response should be:
  """
    foo: "bar"
  """
    And The response should contain "foo"
    And The response should contain "foo" with value "bar"
    And The response should not contain "foo" with value "wee"
    And The response should not contain "bar"

  Scenario: With array
    Given I call GET "http://localhost:8080/withArray"
    Then The response status should be 200
    And The response should contain array with size 3
    And The response should contain 3 entities
    And The response should contain at least 2 entity
    And The response should contain at most 4 entities
    And The response should contain more than 2 entities
    And The response should contain less than 4 entities
    And The response should be array:
  """
    - foo: "bar"
    - foo: 3
    - foos:
      - bar
      - wee
  """
    And Response entity "[0]" should contain "foo"
    And Response entity "[0]" should not contain "bar"
    And Response entity "[0]" should contain "foo" with value "bar"
    And Response entity "[0]" should not contain "foo" with value "wee"
    And Response entity "[2].foos" should contain array:
  """
    - bar
    - wee
  """
    And Response entity "[2].foos" should contain 2 entities
    And Response entity "[2].foos" should contain at least 1 entity
    And Response entity "[2].foos" should contain at most 3 entities
    And Response entity "[2].foos" should contain more than 1 entities
    And Response entity "[2].foos" should contain less than 3 entities