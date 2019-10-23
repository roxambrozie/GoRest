@AlbumSmoke
Feature: Smoke testing the albums endpoint

  Background: Creating an album

    When I prepare my prerequisites
    When I create a new album with my user id as expected, I provide the title Vacation photos
    Then I check that the status code from the response body is 201

  Scenario: Getting album details

    When I retrieve a single album based on the id: as expected
    Then I check that the status code from the response body is 200

  Scenario: Deleting album details

    When I delete a single album based on the id: as expected
    Then I check that the status code from the response body is 204

  Scenario: Updating album details

    When I update a single album with my user id as expected and the new title Summer
    Then I check that the status code from the response body is 200