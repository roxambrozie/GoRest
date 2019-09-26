package gorest.smoketests.stepdefinitions;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import services.gorest.steps.*;
import utils.methods.ReusableMethods;

@WithTags({
        @WithTag(type = "service", name = "GoRest"),
        @WithTag(type = "type", name = "Smoke"),
        @WithTag(type = "type", name = "Regression")
})
public class UserStepsDefinition {

    @Steps
    private PostUserSteps postUserSteps;

    @Steps
    private GetUserSteps getUserSteps;

    @Steps
    private DeleteUserSteps deleteUserSteps;

    @Steps
    private UpdateUserSteps updateUserSteps;

    @Steps
    private CommonSteps commonSteps;

    @Steps
    private ReusableMethods reusableMethods;


    @When("^I create a new user for the GoRest API with email (.*), first name (.*), last name (.*) and gender (.*)$")
    public void whenCreateUser(String email, String firstName, String lastName, String gender) {
       Response response = postUserSteps.whenCreateNewUser(email, firstName, lastName, gender);
        commonSteps.setResponseToSessionVariable(response);
    }

    @Then("^I check that the status code from the response body is (.*)$")
    public void thenCheckStatusCodeFromResponseBody(int statusCode) {
        commonSteps.validateResponseBodyStatusCode(statusCode);
    }

    @When("^I retrieve a single user based on id (.*)$")
    public void whenGetUserById(int id) {
        Response response = getUserSteps.getUserUsingId(id);
        commonSteps.setResponseToSessionVariable(response);
    }

    @When("^I delete a single user, after I create it, based on id$")
    public void whenDeleteUserById() {
        Response response = deleteUserSteps.deleteCreatedUser();
        commonSteps.setResponseToSessionVariable(response);
    }

    @When("^I update a single user based on id (.*) with the new (.*)$")
    public void whenUpdateUserById(int id, String last_name) {
        Response response = updateUserSteps.whenUpdateUserLastNameUsingId(id, last_name);
        commonSteps.setResponseToSessionVariable(response);
    }

}
