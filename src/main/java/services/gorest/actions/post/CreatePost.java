package services.gorest.actions.post;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import services.gorest.actions.GoRestActions;
import services.gorest.models.Post;
import utils.reusable.specifications.ReusableSpecifications;

public class CreatePost extends GoRestActions {

    private String CREATE_POST_URL = getBaseUri() + POSTS_ENDPOINT;

    @Step("I create a new post")
    public Response createNewPost(Post post) {
        Response response = SerenityRest.rest().given().log().all()
                .spec(ReusableSpecifications.authorizedRequestSpec())
                .baseUri(CREATE_POST_URL)
                .when()
                .body(post)
                .post();
        response.then().log().all().spec(ReusableSpecifications.responseSpec());

        return response;
    }

    @Step("When I create a post with the user id, title and text body")
    public Response whenCreateNewPost(int userId, String title, String body) {
        Post post = new Post(userId, title, body);
        return createNewPost(post);
    }
}
