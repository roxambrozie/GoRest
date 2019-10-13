package services.gorest.actions.post;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import services.gorest.actions.GoRestActions;
import utils.reusable.specifications.ReusableSpecifications;

public class GetPost extends GoRestActions {

    private String GET_POST_URL = getBaseUri() + POSTS_ENDPOINT;

    @Step("I retrieve a single post based on id {0}")
    public Response getPostById(String id) {
        Response response = SerenityRest.rest().given().log().all()
                .spec(ReusableSpecifications.authorizedRequestSpec())
                .baseUri(GET_POST_URL)
                .pathParam("id", id)
                .when()
                .get("/{id}");
        response.then().log().all();

        return response;
    }

}