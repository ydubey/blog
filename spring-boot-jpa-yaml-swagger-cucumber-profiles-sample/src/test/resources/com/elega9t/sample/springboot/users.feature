Feature: Users

  Scenario: request all users
    When I request for all users
    Then I receive a valid list of users
