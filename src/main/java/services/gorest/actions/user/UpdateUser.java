package services.gorest.actions.user;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import services.gorest.actions.GoRestActions;
import services.gorest.models.User;
import utils.reusable.specifications.ReusableSpecifications;

public class UpdateUser extends GoRestActions {

    private String UPDATE_USER_URL = getBaseUri() + USERS_ENDPOINT;

    @Step("When I update a single user based on id {0}")
    public Response whenUpdateUserDetailById(String id, User user) {
        Response response = SerenityRest.rest().given().log().all()
                .spec(ReusableSpecifications.authorizedRequestSpec())
                .baseUri(UPDATE_USER_URL)
                .pathParam("id", id)
                .body(user)
                .when()
                .patch("/{id}");
        response.then().log();

        return response;
    }

    @Step("When I update a single user based on id {0} with all details")
    public Response whenUpdateAllUserDetailsById(String id, User user) {
        Response response = SerenityRest.rest().given().log().all()
                .spec(ReusableSpecifications.authorizedRequestSpec())
                .baseUri(UPDATE_USER_URL)
                .pathParam("id", id)
                .body(user)
                .when()
                .put("/{id}");
        response.then().log();

        return response;
    }

    public Response whenUpdateUsersLastNameUsingId(String id, String last_name) {
        User user = new User();
        user.setLastName(last_name);
        return whenUpdateUserDetailById(id, user);
    }

    public Response whenUpdateAllUserDetailsById(String id,String firstName, String last_name, String email, String status, String gender) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(last_name);
        user.setEmail(email);
        user.setGender(gender);
        user.setStatus(status);

        return whenUpdateAllUserDetailsById(id, user);
    }

}