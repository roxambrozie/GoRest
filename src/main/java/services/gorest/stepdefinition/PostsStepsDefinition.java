package services.gorest.stepdefinition;


import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import services.gorest.actions.post.CreatePost;
import services.gorest.actions.post.DeletePost;
import services.gorest.actions.post.GetPost;
import services.gorest.actions.post.UpdatePost;
import services.gorest.actions.user.CreateUser;
import services.gorest.actions.user.DeleteUser;
import services.gorest.models.responses.GetPostResponse;
import services.gorest.models.responses.GetUserResponse;
import utils.methods.ReusableMethods;

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

    @Steps
    private ReusableMethods reusableMethods;

    @Steps
    private CreateUser createUser;

    @Steps
    private DeleteUser deleteUser;

    @Before("@PostSmoke")
    public void createPrereq() {
        Response response = createUser.whenCreateRandomUser();
        setSessionVariable(VAR_USER_ID, response.as(GetUserResponse.class).getResult().getId());
    }

    @When("^I create a new post with my user id (.*), I provide the title (.*) and add the following body:$")
    public void whenCreatePost(String userId, String title, String body) {
        int uId = Integer.valueOf(reusableMethods.setSessionVariableAsExpected(userId, VAR_USER_ID));
        Response response = createPost.whenCreateNewPost(uId, title, body);
        setSessionVariable(VAR_POST_ID, response.as(GetPostResponse.class).getResult().getId());
        setSessionVariable(VAR_RESPONSE, response);
    }

    @When("^I retrieve a single post based on the id: (.*)$")
    public void whenGetPostById(String postId) {
        reusableMethods.setSessionVariableAsExpected(postId, VAR_POST_ID);
        Response response = getPost.getPostById(getSessionVariable(VAR_POST_ID));
        setSessionVariable(VAR_RESPONSE, response);
    }

    @When("^I delete a single post based on the id: (.*)$")
    public void whenDeletePostById(String postId) {
        reusableMethods.setSessionVariableAsExpected(postId, VAR_POST_ID);
        Response response = deletePost.deletePostUsingId(getSessionVariable(VAR_POST_ID));
        setSessionVariable(VAR_RESPONSE, response);
    }

    @When("^I update a single post using my user id (.*) with the new title (.*)$")
    public void whenUpdatePostById(String userId, String title) {
        int uId = Integer.valueOf(reusableMethods.setSessionVariableAsExpected(userId, VAR_USER_ID));
        Response response = updatePost.whenUpdatePostsTitleUsingId(getSessionVariable(VAR_POST_ID), uId, title);
        setSessionVariable(VAR_RESPONSE, response);
    }


    @After("@PostSmoke")
    public void tearDownDeletePost(Scenario scenario) {
        if (!scenario.getName().equals("Deleting a posts details")) {
            if (getSessionVariable(VAR_POST_ID) != null) {
                deletePost.deletePostUsingId(getSessionVariable(VAR_POST_ID));
            } else {
                System.err.println("The post you want to delete does not exist.");
            }
            deleteUser.deleteUserById(getSessionVariable(VAR_USER_ID));
        }
    }
}