package gorest.post;

import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Test;
import org.junit.runner.RunWith;
import services.gorest.actions.user.CreateUser;
import services.gorest.actions.user.DeleteUser;
import services.gorest.models.User;
import services.gorest.validation.CommonValidations;
import utils.methods.ReusableMethods;

@RunWith(SerenityRunner.class)
@WithTags({
        @WithTag(type = "service", name = "GoRest"),
        @WithTag(type = "type", name = "Smoke"),
        @WithTag(type = "type", name = "Regression")
})
public class PostUserTest {

    @Steps
    private CommonValidations commonValidations;

    @Steps
    private CreateUser createUser;

    @Steps
    private DeleteUser deleteUser;

    @Steps
    private ReusableMethods reusableMethods;

    @Test
    public void createUserTest() {
        User user = new User("email" + reusableMethods.generateRandomInt(10, 100) + "@email.com", "UserName", "Last", "male");
        Response response = createUser.createNewUser(user);
        commonValidations.validateResponseStatusCode(response, 201);
    }
}
