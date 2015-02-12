Feature: Specific Authority

  Background: Get a specific authority id
    When  I request for all authorities
    Then  I receive a valid list of authorities
    And   I use a specific authority from the list

  Scenario: Requests a specific authority
    When  I request for a specific authority
    Then  I receive a valid authority
