@UserSmoke
Feature: Smoke testing the users endpoints

  Background: Creating a user
    When I create a new user with email uniqueol@email.com, first name Dan, last name Smith and gender male
    Then I check that the status code from the response body is 201


  Scenario: Getting a users details

    When I retrieve a single user based on id
    Then I check that the status code from the response body is 200


  Scenario: Deleting a users details

    When I delete a single user based on id
    Then I check that the status code from the response body is 204


  Scenario: Updating a users details

    When I update a single user based on id with the new last name Scottish
    Then I check that the status code from the response body is 200


