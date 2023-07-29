@Sogeti
Feature: Sogeti UI Test cases



  @UI
  Scenario: Testcase 1 validating servies and automation links
    Given I want to launch Sogeti home page
    When I navigate to Services and Automation link
    Then I verify Automation text in Automation page
    And I verify the highlight of Services and Automation links
    
  @UI
  Scenario: Testcase 2 validating Contact us form filling
    Given I want to launch Sogeti home page
    When I navigate to Services and Automation link
    When I enter the details of Contact us Form
    And I click the checkboxes of I agree and I am not Robot
    
  @sogetiUI
  Scenario: Testcase 3 validating Country specific links
    Given I want to launch Sogeti home page
    When I click Worldwide link
    Then I verify Country specific links
    

 
