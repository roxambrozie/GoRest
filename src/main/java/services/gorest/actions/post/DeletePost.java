package services.gorest.actions.post;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import services.gorest.actions.GoRestActions;
import services.gorest.models.responses.GetPostResponse;
import utils.reusable.specifications.ReusableSpecifications;

public class DeletePost extends GoRestActions {

    private String DELETE_POST_URL = getBaseUri() + POSTS_ENDPOINT;

    @Step("I delete a single post based on id {0}")
    public Response deletePostUsingId(int id) {
        Response response = SerenityRest.rest().given().log().all()
                .spec(ReusableSpecifications.authorizedRequestSpec())
                .baseUri(DELETE_POST_URL)
                .pathParam("id", id)
                .when()
                .delete("/{id}");
        response.then().log().all().spec(ReusableSpecifications.responseSpec());

        return response;
    }

    @Step("I delete a created post based on id")
    public Response deleteCreatedPost(Response response) {
        return deletePostUsingId(response.as(GetPostResponse.class).getResult().getId());
    }
}
