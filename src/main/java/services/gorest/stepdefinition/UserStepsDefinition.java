package services.gorest.stepdefinition;

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
import services.gorest.models.User;
import services.gorest.validation.CommonValidations;
import utils.methods.ReusableMethods;

import static utils.variables.SessionVariableManager.getSessionVariable;
import static utils.variables.SessionVariableManager.setSessionVariable;
import static utils.variables.SessionVariables.VAR_RESPONSE;

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

    @Steps
    private ReusableMethods reusableMethods;


    @When("^I create a new user for the GoRest API with email (.*), first name (.*), last name (.*) and gender (.*)$")
    public void whenCreateUser(String email, String firstName, String lastName, String gender) {
        Response response = createUser.whenCreateNewUser(email, firstName, lastName, gender);
      //TODO  setSessionVariable();
        setSessionVariable(VAR_RESPONSE, response);
    }

    @Then("^I check that the status code from the response body is (.*)$")
    public void thenCheckStatusCodeFromResponseBody(int statusCode) {
        commonValidations.validateResponseStatusCode(getSessionVariable(VAR_RESPONSE), statusCode);
    }

    @When("^I retrieve a single user based on id (.*)$")
    public void whenGetUserById(int id) {
        Response response = getUser.getUserUsingId(id);
        setSessionVariable(VAR_RESPONSE, response);
    }

    @When("^I delete a single user based on id$")
    public void whenDeleteUserById() {
        Response response = deleteUser.deleteCreatedUser(getSessionVariable(VAR_RESPONSE));
        setSessionVariable(VAR_RESPONSE, response);
    }

    @When("^I update a single user based on id (.*) with the new (.*)$")
    public void whenUpdateUserById(int id, String last_name) {
        Response response = updateUser.whenUpdateUserLastNameUsingId(id, last_name);
        setSessionVariable(VAR_RESPONSE, response);
    }

}
