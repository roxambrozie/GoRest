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
    public Response whenUpdateUserUsingId(int id, User user) {
        Response response = SerenityRest.rest().given().log().all()
                .spec(ReusableSpecifications.authorizedRequestSpec())
                .baseUri(UPDATE_USER_URL)
                .pathParam("id", id)
                .body(user)
                .when()
                .patch("/{id}");
        response.then().log().all().spec(ReusableSpecifications.responseSpec());

        return response;
    }

    @Step("When I update the last name of a user ")
    public Response whenUpdateUserLastNameUsingId(int id,String last_name){
        User user = new User();
        user.setLastName(last_name);
        return whenUpdateUserUsingId(id, user);
    }

}
