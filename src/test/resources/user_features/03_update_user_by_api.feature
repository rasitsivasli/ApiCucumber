@End2End
Feature: Update User By Api

@Api
  Scenario: Update User Scenario
  Given set the url for patch request
  And set the expected data for patch request
  When send the put request and get the response
  Then do assertion for put request
