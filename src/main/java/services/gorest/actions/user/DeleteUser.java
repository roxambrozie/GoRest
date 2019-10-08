package services.gorest.actions.user;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import services.gorest.actions.GoRestActions;
import utils.reusable.specifications.ReusableSpecifications;

public class DeleteUser extends GoRestActions {

    private String DELETE_USER_URL = getBaseUri() + USERS_ENDPOINT;

    @Step("I delete a single user based on id {0}")
    public Response deleteUserById(int id) {
        Response response = SerenityRest.rest().given().log().all()
                .spec(ReusableSpecifications.authorizedRequestSpec())
                .baseUri(DELETE_USER_URL)
                .pathParam("id", id)
                .when()
                .delete("/{id}");
        response.then().log().all().spec(ReusableSpecifications.responseSpec());

        return response;
    }

}