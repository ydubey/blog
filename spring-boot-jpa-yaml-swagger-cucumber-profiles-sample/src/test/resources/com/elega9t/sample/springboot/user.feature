Feature: Specific User

  Background: Get a specific user id
    When  I request for all users
    Then  I receive a valid list of users
    And   I use a specific user from the list

  Scenario: Requests a specific user
    When  I request for a specific user
    Then  I receive a valid user
