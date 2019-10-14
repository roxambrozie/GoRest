# Getting started with API testing using Serenity and Cucumber

    This project intends to be an POC for writing automated tests with reporting features in a Behavior Driven Development manner. It is intended for API testing using RestAssured.
	The overall objective is to test GoRest which is a public REST API used to create users that can perform actions like posting, commenting, creating albums and adding photos to the albums. This allows accessing key-value pairs in human readable JSON-Format.
	This REST API is stateless, therefore sessions and cookies should not be used and each request comes with authentication credentials for the user as a secret access token. The access token can be used to uniquely identify and authenticate a user. As a method to prevent Man in the middle attacks and protect the security, all API requests should be sent via HTTPS.

	The testing will revolve around the functionalities of this API:
    1. A user can be created
    2. A user can make a post
    3. A user can comment on a post
    4. A user can create an album
    5. A user can add photos to the album

    Prerequisites
    I have created an account on the GoRest API website to receive an access token. This access token is limited at 60 requests per minute.

    The starter project
    A new Maven project will be created in IntelliJ and the following dependencies will be added: Serenity(including core, junit, rest-assured and cucumber), Junit, slf4j, lombok, and plugins: maven-failsafe, maven-surefire, serenity-maven. Additional dependencies and plugins might be needed as the project evolves.

    Running the tests
    The tests can be run using a created Cucumber Runner with the following configuration:
    Main Class: net.serenitybdd.cucumber.cli.Main
    Glue: net.serenitybdd.cucumber.actors services.gorest.stepdefinition
    Feature: D:/GoRest/src/test/resources/features -. provide here the path to the feature files

    The tests can also be run using the "mvn clean install" command in the maven terminal.