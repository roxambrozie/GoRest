package services.gorest.validation;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import services.gorest.models.responses.GeneralResponse;

public class CommonValidations {

    @Step("Then I check the status code of the response is {1}")
    public void validateResponseStatusCode(Response response, int statusCode) {
        Assert.assertEquals(response.as(GeneralResponse.class).get_meta().getCode(), statusCode);
    }
}