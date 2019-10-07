package services.gorest.actions.user;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import services.gorest.actions.GoRestActions;
import services.gorest.models.User;
import utils.reusable.specifications.ReusableSpecifications;

public class CreateUser extends GoRestActions {

    private String POST_USER_URL = getBaseUri() + USERS_ENDPOINT;

    @Step("I create a new user")
    public Response createNewUser(User user) {
        Response response = SerenityRest.rest().given().log().all()
                .spec(ReusableSpecifications.authorizedRequestSpec())
                .baseUri(POST_USER_URL)
                .contentType(ContentType.JSON)
                .when()
                .body(user)
                .post();
        response.then().log().all().spec(ReusableSpecifications.responseSpec());

        return response;
    }

    @Step("When I create an user with email, first name, last name and gender")
    public Response whenCreateNewUser(String email, String firstName, String lastName, String gender) {
        User user = new User(email, firstName, lastName, gender);
        return createNewUser(user);
    }
}
