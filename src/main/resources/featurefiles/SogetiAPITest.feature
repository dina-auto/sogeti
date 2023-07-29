@Sogeti
Feature: Sogeti API Test cases

  @API
  Scenario: API Test to validate Content type Status code Response time and Response
    Given I want to create the API base setup for Zipcode
    Then I run the request and validate Test status code and content type
    Then I run the request and validate Response time
    Then I run the request and validate Country and State
    Then I run the request and validate Place name for specific post code

  @API
  Scenario: API Response validation with Data Driven Test
    Given I read the excel and have the Data set 
    When I run the request in data driven and validate the response