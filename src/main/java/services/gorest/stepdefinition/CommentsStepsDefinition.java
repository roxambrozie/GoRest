package services.gorest.stepdefinition;

import cucumber.api.java.en.When;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import services.gorest.actions.comment.CreateComment;
import services.gorest.actions.comment.DeleteComment;
import services.gorest.actions.comment.GetComment;
import services.gorest.models.responses.GetCommentResponse;

import static utils.variables.SessionVariableManager.setSessionVariable;
import static utils.variables.SessionVariables.VAR_COMMENT_ID;
import static utils.variables.SessionVariables.VAR_RESPONSE;

public class CommentsStepsDefinition {

    @Steps
    private CreateComment createComment;

    @Steps
    private DeleteComment deleteComment;

    @Steps
    private GetComment getComment;

    @When("^I add a comment for the post with the id (.*), I provide my name (.*), email address (.*) and add the following body:$")
    public void whenCreateComment(int postId, String name, String email, String body) {
        Response response = createComment.whenCreateNewPost(postId, name, email, body);
        setSessionVariable(VAR_COMMENT_ID, response.as(GetCommentResponse.class).getResult().getId());
        setSessionVariable(VAR_RESPONSE, response);
    }


}
