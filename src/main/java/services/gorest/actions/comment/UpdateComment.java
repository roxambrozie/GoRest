package services.gorest.actions.comment;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import services.gorest.actions.GoRestActions;
import services.gorest.models.Comment;
import utils.reusable.specifications.ReusableSpecifications;

public class UpdateComment extends GoRestActions {

    private String UPDATE_COMMENT_URL = getBaseUri() + COMMENTS_ENDPOINT;

    @Step("When I update a comment based on id {0}")
    public Response whenUpdateComment(String id, Comment comment) {
        Response response = SerenityRest.rest().given().log().all()
                .spec(ReusableSpecifications.authorizedRequestSpec())
                .baseUri(UPDATE_COMMENT_URL)
                .pathParam("id", id)
                .body(comment)
                .when()
                .patch("/{id}");
        response.then().log().all();

        return response;
    }

    public Response whenUpdateCommentDetails(String id, String name, String email, String body) {
        Comment comment = new Comment();
        comment.setName(name);
        comment.setEmail(email);
        comment.setBody(body);

        return whenUpdateComment(id, comment);
    }
}
