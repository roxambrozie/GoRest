package services.gorest.actions.user;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import services.gorest.actions.GoRestActions;
import services.gorest.models.responses.GetPostResponse;
import utils.reusable.specifications.ReusableSpecifications;

public class GetUser extends GoRestActions {

    private String GET_USER_URL = getBaseUri() + USERS_ENDPOINT;

    @Step("I retrieve a single user based on id {0}")
    public Response getUserUsingId(int id) {
        Response response = SerenityRest.rest().given().log().all()
                .spec(ReusableSpecifications.authorizedRequestSpec())
                .baseUri(GET_USER_URL)
                .pathParam("id", id)
                .when()
                .get("/{id}");
        response.then().log().all().spec(ReusableSpecifications.responseSpec());

        return response;
    }

    public Response getCreatedUser(Response response) {
        return getUserUsingId(response.as(GetPostResponse.class).getResult().getId());
    }

}
