package services.gorest.actions.comment;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import services.gorest.actions.GoRestActions;
import utils.reusable.specifications.ReusableSpecifications;

public class GetComment extends GoRestActions {

    private String GET_COMMENT_URL = getBaseUri() + COMMENTS_ENDPOINT;

    @Step("I retrieve a single comment based on id {0}")
    public Response getCommentById(String id) {
        Response response = SerenityRest.rest().given().log().all()
                .spec(ReusableSpecifications.authorizedRequestSpec())
                .baseUri(GET_COMMENT_URL)
                .pathParam("id", id)
                .when()
                .get("/{id}");
        response.then().log().all().spec(ReusableSpecifications.responseSpec());

        return response;
    }

}
