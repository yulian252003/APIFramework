Feature: Validating Place APIs

  Scenario: Verify if Place is being Succesfully added using AddPlaceAPI
    Given Add Place Payload
    When user calls "AddPlaceAPI" with Post http request
    Then the API call is success with satus code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
   

    