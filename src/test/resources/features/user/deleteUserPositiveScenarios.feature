@DeleteUserPositiveScenarios
Feature: Delete User Positive Tests

  Background: Creating a user
    When I create a new user with email d.doe100@email.com, first name Dan, last name Smith and gender male
    Then I check that the status code from the response body is 201


  Scenario: Delete user and validate response

    When I delete a single user based on id: as expected
    Then I check that the success from the response body is set to true
    Then I check that the status code from the response body is 204
    Then I check that the message from the response body is The request was handled successfully and the response contains no body content.


  Scenario: Get deleted user and validate all response fields

    When I delete a single user based on id: as expected
    Then I check that the success from the response body is set to true
    Then I check that the status code from the response body is 204
    Then I check that the message from the response body is The request was handled successfully and the response contains no body content.
    When I retrieve a single user with the id: as expected
    Then I check that the success from the response body is set to false
    Then I check that the status code from the response body is 404
    Then I check that the message from the response body is The requested resource does not exist.
    Then I check that the name from the invalid result is Not Found
    Then I check that the message from the invalid result is Object not found:  expected
    Then I check that the code from the invalid result is 0
    Then I check that the status code from the invalid result is 404

