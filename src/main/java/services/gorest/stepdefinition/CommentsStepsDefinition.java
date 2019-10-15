package services.gorest.stepdefinition;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import services.gorest.actions.comment.CreateComment;
import services.gorest.actions.comment.DeleteComment;
import services.gorest.actions.comment.GetComment;
import services.gorest.actions.comment.UpdateComment;
import services.gorest.actions.post.CreatePost;
import services.gorest.actions.post.DeletePost;
import services.gorest.actions.user.CreateUser;
import services.gorest.actions.user.DeleteUser;
import services.gorest.models.responses.GetCommentResponse;
import services.gorest.models.responses.GetPostResponse;
import services.gorest.models.responses.GetUserResponse;

import static utils.methods.ReusableMethods.replaceExpectedWithVariable;
import static utils.variables.SessionVariableManager.getSessionVariable;
import static utils.variables.SessionVariableManager.setSessionVariable;
import static utils.variables.SessionVariables.*;

public class CommentsStepsDefinition {
    private String userId;

    @Steps
    private CreateUser createUser;

    @Steps
    private CreatePost createPost;

    @Steps
    private CreateComment createComment;

    @Steps
    private GetComment getComment;

    @Steps
    private UpdateComment updateComment;

    @Steps
    private DeleteComment deleteComment;

    @Steps
    private DeletePost deletePost;

    @Steps
    private DeleteUser deleteUser;

    @Before("@CommentSmoke")
    public void createPrereq() {
        Response userResponse = createUser.whenCreateRandomUserObject();
        userId = userResponse.as(GetUserResponse.class).getResult().getId();
        Response postResponse = createPost.whenCreateNewPost(Integer.parseInt(userId), "Title", "Body");
        setSessionVariable(VAR_POST_ID, postResponse.as(GetPostResponse.class).getResult().getId());
    }

    @When("^I add a comment for the post with the id (.*), I provide my name (.*), email address (.*) and add the following body:$")
    public void whenCreateComment(String postId, String name, String email, String body) {
        replaceExpectedWithVariable(postId, VAR_POST_ID);
        Response response = createComment.whenCreateNewComment(getSessionVariable(VAR_POST_ID), name, email, body);
        GetCommentResponse getCommentResponse = response.as(GetCommentResponse.class);
        setSessionVariable(VAR_RESPONSE, response);
        if (getSessionVariable(VAR_RESPONSE) != null) {
            setSessionVariable(VAR_COMMENT_ID, getCommentResponse.getResult().getId());
        }
    }

    @When("^I retrieve a single comment based on the id: (.*)$")
    public void whenGetCommentById(String commentId) {
        replaceExpectedWithVariable(commentId, VAR_COMMENT_ID);
        Response response = getComment.getCommentById(getSessionVariable(VAR_COMMENT_ID));
        setSessionVariable(VAR_RESPONSE, response);
    }

    @When("^I delete a single comment based on the id: (.*)$")
    public void whenDeletePostById(String commentId) {
        replaceExpectedWithVariable(commentId, VAR_COMMENT_ID);
        Response response = deleteComment.deleteCommentUsingId(getSessionVariable(VAR_COMMENT_ID));
        setSessionVariable(VAR_RESPONSE, response);
    }

    @When("^I update a single comment based on the id: (.*) with the new name (.*), email (.*) and body: (.*)$")
    public void whenUpdatePostById(String commentId, String name, String email, String body) {
        replaceExpectedWithVariable(commentId, VAR_COMMENT_ID);
        Response response = updateComment.whenUpdateCommentDetails(getSessionVariable(VAR_COMMENT_ID), name, email, body);
        setSessionVariable(VAR_RESPONSE, response);
    }

    @After("@CommentSmoke")
    public void tearDownDeletePost(Scenario scenario) {
        if (!scenario.getName().equals("Deleting comment details")) {
            Response response = getComment.getCommentById(getSessionVariable(VAR_COMMENT_ID));
            if (response.as(GetCommentResponse.class).getMeta().getCode() == 200) {
                deletePost.deletePostUsingId(getSessionVariable(VAR_POST_ID));

            } else {
                System.err.println("The comment you want to delete does not exist.");
            }
            deleteUser.deleteUserById(userId);
        }
    }
}
