@CreateUserPositive @UserTearDown
Feature: Create User Positive Tests

  Scenario: Create user with the mandatory fields provided

    When I create a new user with email jane.smith01@email.com, first name Jane, last name Smith and gender female
    Then I check that the status code from the response body is 201
    When I retrieve a single user with the id: as expected
    Then I check that the status code from the response body is 200

  Scenario: Create user with the all the fields provided

    When I create a new user with first name Jane, last name Doe, gender female, date of birth 20.10.1990, email jane.doe1@email.com, phone number 0040990099, website https://www.janedoe.com/, address 4123 RI and status active
    Then I check that the status code from the response body is 201
    When I retrieve a single user with the id: as expected
    Then I check that the status code from the response body is 200

  Scenario: Create user aged 11 with all the fields provided

    When I create a new user with first name Mapel, last name Doe, gender female, date of birth October 24, 2008, email jane.doe1@email.com, phone number 0040990099, website https://www.janedoe.com/, address 4123 RI and status active
    Then I check that the status code from the response body is 201
    When I retrieve a single user with the id: as expected
    Then I check that the status code from the response body is 200

  Scenario: Create a user aged 11 with the mandatory fields

    When I create a new user with first name Charlie, last name Brown, gender male, date of birth October 24, 2008 and email charlie.brown@email.com
    Then I check that the status code from the response body is 201
    When I retrieve a single user with the id: as expected
    Then I check that the status code from the response body is 200

  Scenario Outline: Create a user with the mandatory fields and valid age using different date formats

    When I create a new user with first name Charlie, last name Brown, gender male, date of birth <dob> and email charlie.b@email.com
    Then I check that the status code from the response body is 201
    When I retrieve a single user with the id: as expected
    Then I check that the status code from the response body is 200

    Examples:
      | dob                |
      | 10/24/2000         |
      | 2000/10/24         |
      | October 24, 2000   |
      | October 24th, 2000 |
      | Oct 24, 2000       |


  Scenario: Validate created users name

    When I create a new user with email jane.smith01@email.com, first name Jane, last name Smith and gender female
    Then I check that the status code from the response body is 201
    Then I check the name of the created user is Jane Smith

  Scenario: Validate created users email

    When I create a new user with email jane.smith01@email.com, first name Jane, last name Smith and gender female
    Then I check that the status code from the response body is 201
    Then I check the email of the created user is jane.smith01@email.com

  Scenario: Validate created users gender

    When I create a new user with email jane.smith01@email.com, first name Jane, last name Smith and gender female
    Then I check that the status code from the response body is 201
    Then I check the gender of the created user is female

  Scenario: Validate created users date of birth

    When I create a new user with first name Charlie, last name Brown, gender male, date of birth October 24, 2008 and email charlie.b@email.com
    Then I check that the status code from the response body is 201
    Then I check the date of birth of the created user is 2008-10-24

  Scenario: Validate created users mandatory fields

    When I create a new user with email jane.smith01@email.com, first name Jane, last name Smith and gender female
    Then I check that the status code from the response body is 201
    Then I check the name of the created user is Jane Smith
    Then I check the email of the created user is jane.smith01@email.com
    Then I check the gender of the created user is female

  Scenario: Validate created users details

    When I create a new user with first name Mapel, last name Doe, gender female, date of birth October 24, 2008, email mapel.doe11@email.com, phone number 0040990099, website https://www.janedoe.com/, address 4123 RI and status active
    Then I check that the status code from the response body is 201
    Then I check the name of the created user is Mapel Doe
    Then I check the gender of the created user is female
    Then I check the date of birth of the created user is 2008-10-24
    Then I check the email of the created user is mapel.doe11@email.com
    Then I check the phone of the created user is 0040990099
    Then I check the website of the created user is https://www.janedoe.com/
    Then I check the address of the created user is 4123 RI
    Then I check the status of the created user is active


