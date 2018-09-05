Feature: Features of my service
  A ride-sharing company needs a system to manage their users and journeys.
  A software engineering team is working on user stories below.

  Background:
    Given drivers exist:
      | id | firstname | lastname |
      | 1  | pika      | chu      |
      | 2  | sala      | meche    |
      | 3  | cara      | puce     |
    Given passengers exist:
      | id | firstname | lastname |
      | 1  | bulbi     | zarre    |
      | 2  | germi     | gnion    |
      | 3  | taupi     | coeur    |

  Scenario Outline: Manage driver
    Given as new <driver>
    Then add my profile into the system
    And modify it
    And delete it
    Examples:
      | id | firstname | lastname |
      | 4  | rondou    | dou      |

  Scenario Outline: Manage passenger
    Given as new <passenger>
    Then add my profile into the system
    And modify it
    And delete it
    Examples:
      | id | firstname | lastname |
      | 4  | papi      | lusion   |

