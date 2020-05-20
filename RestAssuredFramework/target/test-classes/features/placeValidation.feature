Feature: Validating Place API's  

@AddPlace
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
       Given Add Place Payload with "<name>" "<language>" "<address>"
       When user calls "AddPlaceApi" with "POST" http request
       Then the API call is successs with status code 200
       And "status" in response body is "OK"
       And "scope" in response body is "APP"
       And verify place_id created using with "<name>" using httpMethod "GetPlaceApi"
       
      
Examples:
   
            |name   |language|address|
            |inHouse|Kannada |Dharwad|
           #|outHouse|English|Hubli  |

@DeletePlace    
Scenario: Verify if delete place api is working
        Given delete place with payload
        When user calls "DeletePlaceApi" with "POST" http request
        Then the API call is successs with status code 200
        And "status" in response body is "OK"