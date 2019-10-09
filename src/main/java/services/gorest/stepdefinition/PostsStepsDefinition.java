package services.gorest.stepdefinition;


import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import services.gorest.actions.post.CreatePost;
import services.gorest.actions.post.DeletePost;
import services.gorest.actions.post.GetPost;
import services.gorest.actions.post.UpdatePost;
import services.gorest.models.responses.GetPostResponse;
import services.gorest.models.responses.GetUserResponse;

import static utils.variables.SessionVariableManager.getSessionVariable;
import static utils.variables.SessionVariableManager.setSessionVariable;
import static utils.variables.SessionVariables.*;

public class PostsStepsDefinition {

    @Steps
    private CreatePost createPost;

    @Steps
    private GetPost getPost;

    @Steps
    private UpdatePost updatePost;

    @Steps
    private DeletePost deletePost;

    @When("^I create a new post with my user id (.*), I provide the title (.*) and add the following body:$")
    public void whenCreatePost(int userId, String title, String body) {
        Response response = createPost.whenCreateNewPost(userId, title, body);
        setSessionVariable(VAR_POST_ID, response.as(GetPostResponse.class).getResult().getId());
        setSessionVariable(VAR_RESPONSE, response);
    }

    @When("^I retrieve a single post based on the id: (.*)$")
    public void whenGetPostById(String postId) {
        if (postId.equalsIgnoreCase("as expected")) {
            postId = getSessionVariable(VAR_POST_ID);
        }
        Response response = getPost.getPostById(postId);
        setSessionVariable(VAR_RESPONSE, response);
    }

    @When("^I delete a single post based on the id: (.*)$")
    public void whenDeletePostById(String postId) {
        if (postId.equalsIgnoreCase("as expected")) {
            postId = getSessionVariable(VAR_POST_ID);
        }
        Response response = deletePost.deletePostUsingId(getSessionVariable(VAR_POST_ID));
        setSessionVariable(VAR_RESPONSE, response);
    }

    @When("^I update a single post using my user id (.*) with the new title (.*)$")
    public void whenUpdatePostById(int userId, String title) {
        Response response = updatePost.whenUpdatePostsTitleUsingId(getSessionVariable(VAR_POST_ID), userId, title);
        setSessionVariable(VAR_RESPONSE, response);
    }

    @After("@PostSmoke")
    public void tearDownDeletePost(Scenario scenario) {
        if (!scenario.getName().equals("Deleting a posts details")) {
            getPost.getPostById(getSessionVariable(VAR_POST_ID));
            deletePost.deletePostUsingId(getSessionVariable(VAR_POST_ID));
        } else {
            System.err.println("The post you want to delete does not exist.");
        }
    }
}