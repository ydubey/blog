Feature: Authorities

  Scenario: request all authorities
    When I request for all authorities
    Then I receive a valid list of authorities
