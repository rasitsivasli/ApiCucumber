@End2End
Feature: Get User By Api

@ReadUser @Api # Before ("@Api") methodunun bu method öncesi calisabilmesi icin bu annatotion gerekli. Aksi takdirde spec obj. null kalir.
  Scenario: Read User
  Given set the url for get request
  And set the expected data for get request
  When send the get request and get the response
  Then do assertion for get request
