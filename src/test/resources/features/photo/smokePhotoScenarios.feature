@PhotoSmoke
Feature: Smoke testing the photos endpoint

  Background: Creating a photo

    When I prepare my prerequisites for adding a photo
    When I add a photo for the album with the id as expected, I provide the title Summer Sunset, url https://lorempixel.com/1024/768/abstract/?73813 and the following thumbnail: https://lorempixel.com/150/150/abstract/?60104
    Then I check that the status code from the response body is 201

  Scenario: Getting photo details

    When I retrieve a single photo based on the id: as expected
    Then I check that the status code from the response body is 200

  Scenario: Deleting photo details

    When I delete a single photo based on the id: as expected
    Then I check that the status code from the response body is 204

  Scenario: Updating photo details

    When I update a single photo based on the id: as expected, from the album with id as expected with the new title Summer, url https://lorempixel.com/1024/768/abstract/?73813 and thumbnail: https://lorempixel.com/150/150/abstract/?60104
    Then I check that the status code from the response body is 200