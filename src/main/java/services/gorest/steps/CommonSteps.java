package services.gorest.steps;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import services.gorest.pojo.User;
import services.gorest.pojo.responses.GetUserResponse;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static utils.variables.SessionVariableManager.getSessionVariable;
import static utils.variables.SessionVariableManager.setSessionVariable;
import static utils.variables.SessionVariables.VAR_RESPONSE;

public class CommonSteps {

    @Step("Then I check the status code of the response when I perform this action is {1}")
    public void validateResponseStatusCode(Response response, int statusCode) {
        assertThat(response.as(GetUserResponse.class).get_meta().getCode(), equalTo(statusCode));
    }

    @Step("Then I check the response has the {1} status code")
    public void validateResponseBodyStatusCode(int code) {
        Response response = getSessionVariable(VAR_RESPONSE);
        Assert.assertEquals(code, response.as(GetUserResponse.class).get_meta().getCode());
    }

    @Step("Set session variable ")
    public void setResponseToSessionVariable(Response response) {
        setSessionVariable(VAR_RESPONSE, response);
    }

    public User createValidUserObject(String email, String firstName, String lastName, String gender) {
        User myUser = new User();
        myUser.setEmail(email);
        myUser.setFirstName(firstName);
        myUser.setLastName(lastName);
        myUser.setGender(gender);

        return myUser;
    }

}
