package services.gorest.stepdefinition;

import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Steps;
import services.gorest.validation.CommonValidations;

import static utils.variables.SessionVariableManager.getSessionVariable;
import static utils.variables.SessionVariables.VAR_RESPONSE;

public class CommonStepDefinitions {

    @Steps
    private CommonValidations commonValidations;

    @Then("^I check that the status code from the response body is (.*)$")
    public void thenCheckStatusCodeFromResponseBody(int statusCode) {
        commonValidations.validateResponseStatusCode(getSessionVariable(VAR_RESPONSE), statusCode);
    }

    @Then("^I check that the success from the response body is set to (.*)$")
    public void thenCheckStatusCodeFromResponseBody(Boolean success) {
        commonValidations.validateCallSuccess(getSessionVariable(VAR_RESPONSE), success);
    }

    @Then("^I check that the message from the response body is (.*)$")
    public void thenCheckStatusCodeFromResponseBody(String message) {
        commonValidations.validateMessage(getSessionVariable(VAR_RESPONSE), message);
    }


}
