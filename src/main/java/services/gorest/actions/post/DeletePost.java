package services.gorest.actions.post;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import services.gorest.actions.GoRestActions;
import utils.reusable.specifications.ReusableSpecifications;

public class DeletePost extends GoRestActions {

    private String DELETE_POST_URL = getBaseUri() + POSTS_ENDPOINT;

    @Step("I delete a single post based on id {0}")
    public Response deletePostUsingId(String id) {
        Response response = SerenityRest.rest().given().log().all()
                .spec(ReusableSpecifications.authorizedRequestSpec())
                .baseUri(DELETE_POST_URL)
                .pathParam("id", id)
                .when()
                .delete("/{id}");
        response.then().log().all();

        return response;
    }

}