package services.gorest.actions.comment;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import services.gorest.actions.GoRestActions;
import utils.reusable.specifications.ReusableSpecifications;

public class DeleteComment extends GoRestActions {

    private String DELETE_COMMENT_URL = getBaseUri() + COMMENTS_ENDPOINT;

    @Step("I delete a single comment based on id {0}")
    public Response deleteCommentUsingId(String id) {
        Response response = SerenityRest.rest().given().log().all()
                .spec(ReusableSpecifications.authorizedRequestSpec())
                .baseUri(DELETE_COMMENT_URL)
                .pathParam("id", id)
                .when()
                .delete("/{id}");
        response.then().log().all().spec(ReusableSpecifications.responseSpec());

        return response;
    }

}
