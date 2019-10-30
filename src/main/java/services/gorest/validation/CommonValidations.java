package services.gorest.validation;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import services.gorest.models.responses.GeneralResponse;
import services.gorest.models.responses.InvalidResponse;

public class CommonValidations {

    @Step("Then I check the status code of the response is {1}")
    public void validateResponseStatusCode(Response response, int statusCode) {
        Assert.assertEquals(statusCode, response.as(GeneralResponse.class).get_meta().getCode());
    }

    @Step("Then I check the success of the method is set to {1}")
    public void validateCallSuccess(Response response, Boolean success) {
        Assert.assertEquals(success, response.as(GeneralResponse.class).get_meta().getSuccess());
    }

    @Step("Then I check the message is {1}")
    public void validateMessage(Response response, String message) {
        Assert.assertEquals(message, response.as(GeneralResponse.class).get_meta().getMessage());
    }

    @Step("Then I check the name from the invalid result is {1}")
    public void validateInvalidResultName(Response response, String name) {
        Assert.assertEquals(name, response.as(InvalidResponse.class).getResult().getName());
    }

    @Step("Then I check the message from the invalid result is {1}")
    public void validateInvalidResultMessage(Response response, String message) {
        Assert.assertEquals(message, response.as(InvalidResponse.class).getResult().getMessage());
    }

    @Step("Then I check the code from the invalid result is {1}")
    public void validateInvalidResultCode(Response response, String code) {
        Assert.assertEquals(code, response.as(InvalidResponse.class).getResult().getCode());
    }

    @Step("Then I check the status from the invalid result is {1}")
    public void validateInvalidResultStatus(Response response, String status) {
        Assert.assertEquals(status, response.as(InvalidResponse.class).getResult().getStatus());
    }
}