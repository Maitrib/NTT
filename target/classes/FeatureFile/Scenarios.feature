Feature: Live Scores Update

  Background:
  
	@TestAssignment
  Scenario: Verify live scores update in real-time
    Given I am on the ESPN Cricinfo homepage
    When I wait for the score update interval
    Then the live scores should update automatically
    And the scores should be correct


 
