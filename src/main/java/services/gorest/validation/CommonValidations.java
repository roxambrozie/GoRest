package services.gorest.validation;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import services.gorest.models.responses.GeneralResponse;

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
}