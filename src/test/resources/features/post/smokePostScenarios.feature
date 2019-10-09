@PostSmoke
Feature: Smoke testing the posts endpoint

  Background: Creating a post
    When I create a new post with my user id 179, I provide the title NASA All-Electric Aircraft and add the following body:
     """
   The first all-electric configuration of NASA’s X-57 Maxwell now is at
 the agency’s Armstrong Flight Research Center in Edwards, California.
    """
    Then I check that the status code from the response body is 201


  Scenario: Getting a posts details

    When I retrieve a single post based on id
    Then I check that the status code from the response body is 200


  Scenario: Deleting a posts details

    When I delete a single post based on id
    Then I check that the status code from the response body is 204


  Scenario: Updating a posts details

    When I update a single post using my user id 179 with the new title Electric Aircraft
    Then I check that the status code from the response body is 200