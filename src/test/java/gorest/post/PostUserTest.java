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
import services.gorest.models.User;
import services.gorest.validation.CommonValidations;

import static utils.variables.SessionVariableManager.getSessionVariable;
import static utils.variables.SessionVariableManager.setSessionVariable;
import static utils.variables.SessionVariables.VAR_RESPONSE;

@RunWith(SerenityRunner.class)
@WithTags({
        @WithTag(type = "service", name = "GoRest"),
        @WithTag(type = "type", name = "Smoke"),
        @WithTag(type = "type", name = "Regression"),
        @WithTag(type = "type", name = "User")
})
public class PostUserTest {

    private User myUser = new User();

    @Steps
    private CommonValidations commonValidations;

    @Steps
    private CreateUser createUser;

    @Steps
    private DeleteUser deleteUser;

    @Before
    public void createPrereq() {
        myUser.setEmail("email@myemail.com");
        myUser.setFirst_name("Isaac");
        myUser.setLast_name("Asimov");
        myUser.setGender("male");
    }

    @Test
    public void createUserTest() {
        Response response = createUser.createNewUser(myUser);
        setSessionVariable(VAR_RESPONSE, response);
        commonValidations.validateResponseStatusCode(response, 201);
    }

    @After
    public void tearDown() {
        deleteUser.deleteCreatedUser(getSessionVariable(VAR_RESPONSE));
    }
}
