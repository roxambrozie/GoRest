@CommentSmoke
Feature: Smoke testing the comments endpoint

  Scenario: Creating a comment
    When I add a comment for the post with the id 1799, I provide my name Elon Musk, email address email@email.com and add the following body:
     """
   The first all-electric configuration of NASA’s X-57 Maxwell now is at
 the agency’s Armstrong Flight Research Center in Edwards, California.
    """
    Then I check that the status code from the response body is 201

