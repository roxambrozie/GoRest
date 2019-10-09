package services.gorest.actions.comment;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import services.gorest.actions.GoRestActions;
import services.gorest.models.Comment;
import utils.reusable.specifications.ReusableSpecifications;

public class CreateComment extends GoRestActions {

    private String CREATE_COMMENT_URL = getBaseUri() + COMMENTS_ENDPOINT;


    @Step("I add a new comment")
    public Response createNewComment(Comment comment) {
        Response response = SerenityRest.rest().given().log().all()
                .spec(ReusableSpecifications.authorizedRequestSpec())
                .baseUri(CREATE_COMMENT_URL)
                .when()
                .body(comment)
                .post();
        response.then().log().all().spec(ReusableSpecifications.responseSpec());

        return response;
    }

    @Step("When I add a comment to a post with the post id, name, email and body")
    public Response whenCreateNewPost(int postId, String name, String title, String body) {
        Comment comment = new Comment(postId, name, title, body);
        return createNewComment(comment);
    }
}

