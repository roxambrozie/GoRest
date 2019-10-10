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

import static utils.variables.SessionVariableManager.getSessionVariable;
import static utils.variables.SessionVariableManager.setSessionVariable;
import static utils.variables.SessionVariables.VAR_RESPONSE;
import static utils.variables.SessionVariables.VAR_USER_ID;

@RunWith(SerenityRunner.class)
@WithTags({
        @WithTag(type = "service", name = "GoRest"),
        @WithTag(type = "type", name = "Smoke"),
        @WithTag(type = "type", name = "Regression")
})
public class PostUserTest {

    private User myUser = new User();

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
        myUser.setEmail("emaisld9hl@myemail.com");
        myUser.setFirstName("Isaac");
        myUser.setLastName("Asimov");
        myUser.setGender("male");
    }

    @Test
    public void createUserTest() {
        Response response = createUser.createNewUser(myUser);
        commonValidations.validateResponseStatusCode(response, 201);
        setSessionVariable(VAR_RESPONSE, response);
        setSessionVariable(VAR_USER_ID, response.as(GetUserResponse.class).getResult().getId());
    }

    @After
    public void tearDown() {
        getUser.getUserById(getSessionVariable(VAR_USER_ID));
        deleteUser.deleteUserById(getSessionVariable(VAR_USER_ID));
    }

}