@GetUserPositiveScenarios  @UserTearDown
Feature: Get User Positive Tests

  Background: Creating a user
    When I create a new user with first name Jane, last name Doe, gender female, date of birth 20.10.1990, email jane.doe1@email.com, phone number 0040990099, website https://www.janedoe.com/, address 4123 RI and status active
    Then I check that the status code from the response body is 201


  Scenario: Get user and validate name

    When I retrieve a single user with the id: as expected
    Then I check that the status code from the response body is 200
    Then I check the name of the created user is Jane Doe

  Scenario: Get user and validate email

    When I retrieve a single user with the id: as expected
    Then I check that the status code from the response body is 200
    Then I check the email of the created user is jane.doe1@email.com

  Scenario: Get user and validate gender

    When I retrieve a single user with the id: as expected
    Then I check that the status code from the response body is 200
    Then I check the gender of the created user is female

  Scenario: Get user and validate date of birth

    When I retrieve a single user with the id: as expected
    Then I check that the status code from the response body is 200
    Then I check the date of birth of the created user is 1990-10-20

  Scenario: Get user and validate that the id of the created user is the same as the id of the retrieved user

    When I retrieve a single user with the id: as expected
    Then I check that the status code from the response body is 200
    Then I check the id of the created user is as expected

  Scenario: Get user and validate mandatory fields

    When I retrieve a single user with the id: as expected
    Then I check that the status code from the response body is 200
    Then I check the name of the created user is Jane Doe
    Then I check the email of the created user is jane.doe1@email.com
    Then I check the gender of the created user is female

  Scenario: Validate retrieved users details

    When I retrieve a single user with the id: as expected
    Then I check that the status code from the response body is 200
    Then I check the name of the created user is Jane Doe
    Then I check the gender of the created user is female
    Then I check the date of birth of the created user is 1990-10-20
    Then I check the email of the created user is jane.doe1@email.com
    Then I check the phone of the created user is 0040990099
    Then I check the website of the created user is https://www.janedoe.com/
    Then I check the address of the created user is 4123 RI
    Then I check the status of the created user is active

  Scenario: Get user and validate success field

    When I retrieve a single user with the id: as expected
    Then I check that the success from the response body is set to true
    Then I check that the status code from the response body is 200

  Scenario: Get user and validate message field

    When I retrieve a single user with the id: as expected
    Then I check that the message from the response body is OK. Everything worked as expected.
    Then I check that the status code from the response body is 200

  Scenario: Validate retrieved users details, success and message

    When I retrieve a single user with the id: as expected
    Then I check that the success from the response body is set to true
    Then I check that the message from the response body is OK. Everything worked as expected.
    Then I check that the status code from the response body is 200
    Then I check the name of the created user is Jane Doe
    Then I check the gender of the created user is female
    Then I check the date of birth of the created user is 1990-10-20
    Then I check the email of the created user is jane.doe1@email.com
    Then I check the phone of the created user is 0040990099
    Then I check the website of the created user is https://www.janedoe.com/
    Then I check the address of the created user is 4123 RI
    Then I check the status of the created user is active