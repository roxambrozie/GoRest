@UpdateUserPositiveScenarios  @UserTearDown
Feature: Update User Positive Tests

  Background: Creating a user
    When I create a new user with first name Jane, last name Doe, gender female, date of birth 20.10.1990, email jane.doe1@email.com, phone number 0040990099, website https://www.janedoe.com/, address 4123 RI and status active
    Then I check that the status code from the response body is 201

  Scenario: Updating user last name

    When I update the user with id as expected with the new last name Smith
    Then I check that the status code from the response body is 200
    When I retrieve a single user with the id: as expected
    Then I check that the status code from the response body is 200
    Then I check the name of the created user is Jane Smith


  Scenario: Updating user mandatory details

    When I update the user with id as expected with first name Daniela, last name Johnson, email d.jane@email.com, status active and gender female
    Then I check that the status code from the response body is 200
    When I retrieve a single user with the id: as expected
    Then I check that the status code from the response body is 200
    Then I check the name of the created user is Daniela Johnson
    Then I check the email of the created user is d.jane@email.com
    Then I check the status of the created user is active
    Then I check the gender of the created user is female


  Scenario: Updating all user details

    When I update the user with id as expected with first name John, last name Doe, gender male, date of birth 20.10.1993, email john_doe@email.com, phone 0040990097, website https://www.johndoe.com/, address 5123 RI and status inactive
    Then I check that the status code from the response body is 200
    When I retrieve a single user with the id: as expected
    Then I check that the status code from the response body is 200
    Then I check the name of the created user is John Doe
    Then I check the gender of the created user is male
    Then I check the date of birth of the created user is 1993-10-20
    Then I check the email of the created user is john_doe@email.com
    Then I check the phone of the created user is 0040990097
    Then I check the website of the created user is https://www.johndoe.com/
    Then I check the address of the created user is 5123 RI
    Then I check the status of the created user is inactive
