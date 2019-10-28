package services.gorest.stepdefinition;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import services.gorest.actions.user.CreateUser;
import services.gorest.actions.user.DeleteUser;
import services.gorest.actions.user.GetUser;
import services.gorest.actions.user.UpdateUser;
import services.gorest.models.responses.GetUserResponse;
import services.gorest.validation.CommonValidations;
import services.gorest.validation.UserValidations;
import utils.methods.ReusableMethods;

import static utils.methods.ReusableMethods.replaceExpectedWithVariable;
import static utils.variables.SessionVariableManager.getSessionVariable;
import static utils.variables.SessionVariableManager.setSessionVariable;
import static utils.variables.SessionVariables.*;


public class UserStepsDefinition {

    @Steps
    private CreateUser createUser;

    @Steps
    private GetUser getUser;

    @Steps
    private DeleteUser deleteUser;

    @Steps
    private UpdateUser updateUser;

    @Steps
    private CommonValidations commonValidations;

    @Steps
    private UserValidations userValidations;

    @Steps
    private ReusableMethods reusableMethods;


    @When("^I create a new user with email (.*), first name (.*), last name (.*) and gender (.*)$")
    public void whenCreateUser(String email, String firstName, String lastName, String gender) {
        Response response = createUser.whenCreateNewUser(email, firstName, lastName, gender);
        GetUserResponse getUserResponse = response.as(GetUserResponse.class);
        setSessionVariable(VAR_USER, getUserResponse.getResult());
        setSessionVariable(VAR_USER_ID, getUserResponse.getResult().getId());
        setSessionVariable(VAR_RESPONSE, response);
    }

    @When("^I create a new user with first name (.*), last name (.*), gender (.*), date of birth (.*), email (.*), phone number (.*), website (.*), address (.*) and status (.*)$")
    public void whenCreateUser(String firstName, String lastName, String gender, String dob, String email, String phone, String website, String address, String status) {
        Response response = createUser.whenCreateNewUserWithAllFields(firstName, lastName, gender, dob, email, phone, website, address, status);
        GetUserResponse getUserResponse = response.as(GetUserResponse.class);
        setSessionVariable(VAR_USER, getUserResponse.getResult());
        setSessionVariable(VAR_USER_ID, getUserResponse.getResult().getId());
        setSessionVariable(VAR_RESPONSE, response);
    }

    @When("^I create a new user with first name (.*), last name (.*), gender (.*), date of birth (.*) and email (.*)$")
    public void whenCreateUser(String firstName, String lastName, String gender, String dob, String email) {
        Response response = createUser.whenCreateUserWihMandatoryAndDob(firstName, lastName, gender, dob, email);
        GetUserResponse getUserResponse = response.as(GetUserResponse.class);
        setSessionVariable(VAR_USER, getUserResponse.getResult());
        setSessionVariable(VAR_USER_ID, getUserResponse.getResult().getId());
        setSessionVariable(VAR_RESPONSE, response);
    }

    @When("^I retrieve a single user with the id: (.*)$")
    public void whenGetUserFromResponse(String userId) {
        replaceExpectedWithVariable(userId, VAR_USER_ID);
        Response response = getUser.getUserById(getSessionVariable(VAR_USER_ID));
        setSessionVariable(VAR_RESPONSE, response);
    }

    @When("^I delete a single user based on id: (.*)$")
    public void whenDeleteUserById(String userId) {
        replaceExpectedWithVariable(userId, VAR_USER_ID);
        Response response = deleteUser.deleteUserById(getSessionVariable(VAR_USER_ID));
        setSessionVariable(VAR_RESPONSE, response);
    }

    @When("^I update the user with id (.*) with the new last name (.*)$")
    public void whenUpdateUserById(String userId, String lastName) {
        replaceExpectedWithVariable(userId, VAR_USER_ID);
        Response response = updateUser.whenUpdateUsersLastNameUsingId(getSessionVariable(VAR_USER_ID), lastName);
        setSessionVariable(VAR_RESPONSE, response);
    }

    @When("^I update the user with id (.*) with first name (.*), last name (.*), email (.*), status (.*) and gender (.*)$")
    public void whenUpdateAllUsersDetailsById(String userId, String firstName, String lastName, String email, String status, String gender) {
        replaceExpectedWithVariable(userId, VAR_USER_ID);
        Response response = updateUser.whenUpdateAllUserDetailsById(getSessionVariable(VAR_USER_ID), firstName, lastName, email, status, gender);
        setSessionVariable(VAR_RESPONSE, response);
    }

    @Then("^I check the name of the created user is (.*) (.*)$")
    public void thenCheckName(String firstName, String lastName) {
        userValidations.validateUserName(getSessionVariable(VAR_RESPONSE), firstName, lastName);
    }

    @Then("^I check the email of the created user is (.*)$")
    public void thenCheckEmail(String firstName) {
        userValidations.validateUserEmail(getSessionVariable(VAR_RESPONSE), firstName);
    }

    @Then("^I check the gender of the created user is (.*)$")
    public void thenCheckGender(String gender) {
        userValidations.validateUserGender(getSessionVariable(VAR_RESPONSE), gender);
    }

    @Then("^I check the date of birth of the created user is (.*)$")
    public void thenCheckDateOfBirth(String dob) {
        userValidations.validateUserDateOfBirth(getSessionVariable(VAR_RESPONSE), dob);
    }

    @Then("^I check the phone of the created user is (.*)$")
    public void thenCheckPhone(String phone) {
        userValidations.validateUserPhone(getSessionVariable(VAR_RESPONSE), phone);
    }

    @Then("^I check the website of the created user is (.*)$")
    public void thenCheckWebsite(String website) {
        userValidations.validateUserWebsite(getSessionVariable(VAR_RESPONSE), website);
    }

    @Then("^I check the address of the created user is (.*)$")
    public void thenCheckAddress(String address) {
        userValidations.validateUserAddress(getSessionVariable(VAR_RESPONSE), address);
    }

    @Then("^I check the status of the created user is (.*)$")
    public void thenCheckStatus(String status) {
        userValidations.validateUserStatus(getSessionVariable(VAR_RESPONSE), status);
    }

    @Then("^I check the id of the created user is (.*)$")
    public void thenCheckId(String userId) {
        replaceExpectedWithVariable(userId, VAR_USER_ID);
        userValidations.validateUserId(getSessionVariable(VAR_RESPONSE), getSessionVariable(VAR_USER_ID));
    }

    @After("@UserTearDown")
    public void tearDownDeleteUser(Scenario scenario) {
        if (!scenario.getName().equals("Deleting user details")) {
            Response response = getUser.getUserById(getSessionVariable(VAR_USER_ID));
            if (response.as(GetUserResponse.class).get_meta().getCode() == 200) {
                deleteUser.deleteUserById(getSessionVariable(VAR_USER_ID));
            } else {
                System.err.println("The user does not exist so it cannot be deleted.");
            }
        }
    }

}