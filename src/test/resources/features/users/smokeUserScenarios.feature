@SMOKE
Feature: Smoke testing the users endpoints

  Scenario Outline: Creating a user
    When I create a new user for the GoRest API with email <email>, first name <first_name>, last name <last_name> and gender <gender>
    Then I check that the status code from the response body is 201


    Examples:
      | email           | first_name | last_name | gender |
      | email@email.com | Dan        | Smith     | male   |


  Scenario: Getting a users details
    When I retrieve a single user based on id 1233
    Then I check that the status code from the response body is 200


  Scenario: Deleting a users details
    When I delete a single user, after I create it, based on id
    Then I check that the status code from the response body is 204


  Scenario Outline: Updating a users details
    When I update a single user based on id 1209 with the new <last_name>
    Then I check that the status code from the response body is 200

    Examples:
      | last_name |
      | Smith     |

