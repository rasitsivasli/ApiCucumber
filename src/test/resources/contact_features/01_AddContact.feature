@Contact
Feature: Add Contact Feature

  @AddContact @Api
  Scenario: Add Contact Scenario
    Given set the url for add user
    And set the expected data for add user
    When send the post request and get the response
    Then do assertion for add user

