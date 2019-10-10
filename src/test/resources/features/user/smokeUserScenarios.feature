@UserSmoke
Feature: Smoke testing the users endpoints

  Background: Creating a user
    When I create a new user with email d.gs@email.com, first name Dan, last name Smith and gender male
    Then I check that the status code from the response body is 201


  Scenario: Getting a users details

    When I retrieve a single user with the id: as expected
    Then I check that the status code from the response body is 200


  Scenario: Deleting a users details

    When I delete a single user based on id: as expected
    Then I check that the status code from the response body is 204


  Scenario: Updating a users last name

    When I update the user with id as expected with the new last name Scottish
    Then I check that the status code from the response body is 200


  Scenario: Updating a users details

    When I update the user with id as expected with first name Daniela, last name Johnson, email d.j@email.com, status active and gender female
    Then I check that the status code from the response body is 200


