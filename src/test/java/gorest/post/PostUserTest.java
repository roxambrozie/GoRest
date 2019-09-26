package gorest.post;

import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import services.gorest.pojo.User;
import services.gorest.pojo.responses.GetUserResponse;
import services.gorest.steps.CommonSteps;
import services.gorest.steps.DeleteUserSteps;
import services.gorest.steps.PostUserSteps;
import utils.methods.ReusableMethods;

@RunWith(SerenityRunner.class)
@WithTags({
        @WithTag(type = "service", name = "GoRest"),
        @WithTag(type = "type", name = "Smoke"),
        @WithTag(type = "type", name = "Regression")
})
public class PostUserTest {

    private User user = new User();

    @Steps
    private CommonSteps commonSteps;

    @Steps
    private PostUserSteps postUserSteps;

    @Steps
    private DeleteUserSteps deleteUserSteps;

    @Steps
    private ReusableMethods reusableMethods;

    @Before
    public void createPrerequisites() {
        user = commonSteps.createValidUserObject("email" + reusableMethods.generateRandomInt(10, 100) + "@email.com", "UserName", "Last", "male");
    }

    @Test
    public void createAndDeleteUserTest() {
        Response response = postUserSteps.createNewUser(user);
        commonSteps.validateResponseStatusCode(response, 201);
        deleteUserSteps.deleteUserUsingId(response.as(GetUserResponse.class).getResult().getId());
    }
}
