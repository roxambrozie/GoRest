package services.gorest.actions.post;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import services.gorest.actions.GoRestActions;
import services.gorest.models.Post;
import services.gorest.models.responses.GetPostResponse;
import services.gorest.models.responses.GetUserResponse;
import utils.reusable.specifications.ReusableSpecifications;

public class UpdatePost extends GoRestActions {

    private String UPDATE_POST_URL = getBaseUri() + POSTS_ENDPOINT;

    @Step("When I update a post based on id {0}")
    public Response whenUpdatePostUsingId(int id, Post post) {
        Response response = SerenityRest.rest().given().log().all()
                .spec(ReusableSpecifications.authorizedRequestSpec())
                .baseUri(UPDATE_POST_URL)
                .pathParam("id", id)
                .body(post)
                .when()
                .patch("/{id}");
        response.then().log().all().spec(ReusableSpecifications.responseSpec());

        return response;
    }

    public Response whenUpdatePostsTitleUsingId(Response response, int userId, String title) {
        Post post = new Post();
        post.setUser_id(userId);
        post.setTitle(title);
        return whenUpdatePostUsingId(response.as(GetPostResponse.class).getResult().getId(), post);
    }

}
