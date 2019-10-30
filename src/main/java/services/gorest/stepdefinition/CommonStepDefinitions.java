package services.gorest.stepdefinition;

import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Steps;
import services.gorest.validation.CommonValidations;

import static utils.methods.ReusableMethods.replaceExpectedWithVariable;
import static utils.variables.SessionVariableManager.getSessionVariable;
import static utils.variables.SessionVariables.VAR_RESPONSE;
import static utils.variables.SessionVariables.VAR_USER_ID;

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

    @Then("^I check that the name from the invalid result is (.*)$")
    public void thenCheckNameFromInvalidResult(String name) {
        commonValidations.validateInvalidResultName(getSessionVariable(VAR_RESPONSE), name);
    }

    @Then("^I check that the message from the invalid result is (.*) (.*)$")
    public void thenCheckMessageFromInvalidResult(String message, String id) {
        replaceExpectedWithVariable(id, VAR_USER_ID);
        String entireMessage = message + getSessionVariable(VAR_USER_ID);
        commonValidations.validateInvalidResultMessage(getSessionVariable(VAR_RESPONSE), entireMessage);
    }

    @Then("^I check that the code from the invalid result is (.*)$")
    public void thenCheckCodeFromInvalidResult(String code) {
        commonValidations.validateInvalidResultCode(getSessionVariable(VAR_RESPONSE), code);
    }

    @Then("^I check that the status code from the invalid result is (.*)$")
    public void thenCheckNameFromInvalidStatus(String statusCode) {
        commonValidations.validateInvalidResultStatus(getSessionVariable(VAR_RESPONSE), statusCode);
    }

}
