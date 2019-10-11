package gorest.post;

import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import services.gorest.actions.user.CreateUser;
import services.gorest.actions.user.DeleteUser;
import services.gorest.actions.user.GetUser;
import services.gorest.models.User;
import services.gorest.models.responses.GetUserResponse;
import services.gorest.validation.CommonValidations;

import static utils.methods.ReusableMethods.generateRandomInt;

@RunWith(SerenityRunner.class)
@WithTags({
        @WithTag(type = "service", name = "GoRest"),
        @WithTag(type = "type", name = "Smoke"),
        @WithTag(type = "type", name = "Regression")
})
public class PostUserTest {

    private User myUser = new User();
    private String userId;

    @Steps
    private CommonValidations commonValidations;

    @Steps
    private CreateUser createUser;

    @Steps
    private DeleteUser deleteUser;

    @Steps
    private GetUser getUser;

    @Before
    public void createPrereq() {
        myUser.setFirstName("Isaac");
        myUser.setLastName("Asimov");
        myUser.setGender("male");
        myUser.setEmail(generateRandomInt(100,100000) + "@email.com");
    }

    @Test
    public void createUserTest() {
        Response response = createUser.createNewUser(myUser);
        commonValidations.validateResponseStatusCode(response, 201);
        userId = response.as(GetUserResponse.class).getResult().getId();
    }

    @After
    public void tearDown() {
        Response response = getUser.getUserById(userId);
        commonValidations.validateResponseStatusCode(response, 200);
        deleteUser.deleteUserById(userId);
    }

}