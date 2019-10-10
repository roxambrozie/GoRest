package services.gorest.actions.post;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import services.gorest.actions.GoRestActions;
import services.gorest.models.Post;
import utils.reusable.specifications.ReusableSpecifications;

public class UpdatePost extends GoRestActions {

    private String UPDATE_POST_URL = getBaseUri() + POSTS_ENDPOINT;

    @Step("When I update a post based on id {0}")
    public Response whenUpdatePostUsingId(String id, Post post) {
        Response response = SerenityRest.rest().given().log().all()
                .spec(ReusableSpecifications.authorizedRequestSpec())
                .baseUri(UPDATE_POST_URL)
                .pathParam("id", id)
                .body(post)
                .when()
                .patch("/{id}");
        response.then().log().all();

        return response;
    }

    public Response whenUpdatePostsTitleUsingId(String postId, int userId, String title) {
        Post post = new Post();
        post.setUserId(userId);
        post.setTitle(title);
        return whenUpdatePostUsingId(postId, post);
    }

}