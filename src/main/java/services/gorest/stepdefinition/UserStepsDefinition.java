package services.gorest.stepdefinition;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import services.gorest.actions.user.CreateUser;
import services.gorest.actions.user.DeleteUser;
import services.gorest.actions.user.GetUser;
import services.gorest.actions.user.UpdateUser;
import services.gorest.models.responses.GetUserResponse;
import services.gorest.validation.CommonValidations;

import static utils.variables.SessionVariableManager.getSessionVariable;
import static utils.variables.SessionVariableManager.setSessionVariable;
import static utils.variables.SessionVariables.*;

@WithTags({
        @WithTag(type = "service", name = "GoRest"),
        @WithTag(type = "type", name = "Smoke"),
        @WithTag(type = "type", name = "Regression")
})
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


    @When("^I create a new user with email (.*), first name (.*), last name (.*) and gender (.*)$")
    public void whenCreateUser(String email, String firstName, String lastName, String gender) {
        Response response = createUser.whenCreateNewUser(email, firstName, lastName, gender);
        setSessionVariable(VAR_USER, response.as(GetUserResponse.class).getResult());
        setSessionVariable(VAR_USER_ID, response.as(GetUserResponse.class).getResult().getId());
        setSessionVariable(VAR_RESPONSE, response);
    }


    @Then("^I check that the status code from the response body is (.*)$")
    public void thenCheckStatusCodeFromResponseBody(int statusCode) {
        commonValidations.validateResponseStatusCode(getSessionVariable(VAR_RESPONSE), statusCode);
    }

    @When("^I retrieve a single user with the id: (.*)$")
    public void whenGetUserFromResponse(String userId) {
        if (userId.equalsIgnoreCase("as expected")) {
            userId = getSessionVariable(VAR_USER_ID);
        }
        Response response = getUser.getUserById(getSessionVariable(VAR_USER_ID));
        setSessionVariable(VAR_RESPONSE, response);
    }

    @When("^I delete a single user based on id: (.*)$")
    public void whenDeleteUserById(String userId) {
        if (userId.equalsIgnoreCase("as expected")) {
            userId = getSessionVariable(VAR_USER_ID);
        }
        Response response = deleteUser.deleteUserById(getSessionVariable(VAR_USER_ID));
        setSessionVariable(VAR_RESPONSE, response);
    }

    @When("^I update a users last name to (.*)$")
    public void whenUpdateUserById(String lastName) {
        Response response = updateUser.whenUpdateUsersLastNameUsingId(getSessionVariable(VAR_USER_ID), lastName);
        setSessionVariable(VAR_RESPONSE, response);
    }

    @After("@UserSmoke")
    public void tearDownDeleteUser(Scenario scenario) {
        if (!scenario.getName().equals("Deleting a users details")) {

            deleteUser.deleteUserById(getSessionVariable(VAR_USER_ID));
        }
    }

}