@CommentSmoke
Feature: Smoke testing the comments endpoint

  Background: Creating a comment

    When I prepare my prerequisites for creating a comment
    When I add a comment for the post with the id as expected, I provide my name Elon Musk, email address elon@email.com and add the following body:
     """
   I believe in innovation and that the way you get innovation is you fund research and you learn the basic facts.
   It means that investigation (research). learning, and building on foundational skills and knowledge leads to innovation.
    """
    Then I check that the status code from the response body is 201

  Scenario: Getting a comment details

    When I retrieve a single comment based on the id: as expected
    Then I check that the status code from the response body is 200

  Scenario: Deleting comment details

    When I delete a single comment based on the id: as expected
    Then I check that the status code from the response body is 204

  Scenario: Updating comment details

    When I update a single comment based on the id: as expected with the new name Neil, email neil@email.com and body: I disagree with this.
    Then I check that the status code from the response body is 200