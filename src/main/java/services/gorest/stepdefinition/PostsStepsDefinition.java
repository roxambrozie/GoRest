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
import services.gorest.models.User;
import services.gorest.models.responses.GetPostResponse;
import services.gorest.models.responses.GetUserResponse;
import utils.methods.ReusableMethods;

import static utils.methods.ReusableMethods.setExpectedStringValueFromSessionVariable;
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
        User user;
        user = createUser.whenCreateRandomUserObject();
        Response response = createUser.createNewUser(user);
        setSessionVariable(VAR_USER_ID, response.as(GetUserResponse.class).getResult().getId());
    }

    @When("^I create a new post with my user id (.*), I provide the title (.*) and add the following body:$")
    public void whenCreatePost(String userId, String title, String body) {
        int uId = Integer.valueOf(setExpectedStringValueFromSessionVariable(userId, VAR_USER_ID));
        Response response = createPost.whenCreateNewPost(uId, title, body);
        GetPostResponse getPostResponse = response.as(GetPostResponse.class);
        setSessionVariable(VAR_RESPONSE, response);
        if (getSessionVariable(VAR_RESPONSE) != null) {
            setSessionVariable(VAR_POST_ID, getPostResponse.getResult().getId());
        }
    }

    @When("^I retrieve a single post based on the id: (.*)$")
    public void whenGetPostById(String postId) {
        setExpectedStringValueFromSessionVariable(postId, VAR_POST_ID);
        Response response = getPost.getPostById(getSessionVariable(VAR_POST_ID));
        setSessionVariable(VAR_RESPONSE, response);
    }

    @When("^I delete a single post based on the id: (.*)$")
    public void whenDeletePostById(String postId) {
        setExpectedStringValueFromSessionVariable(postId, VAR_POST_ID);
        Response response = deletePost.deletePostUsingId(getSessionVariable(VAR_POST_ID));
        setSessionVariable(VAR_RESPONSE, response);
    }

    @When("^I update a single post using my user id (.*) with the new title (.*)$")
    public void whenUpdatePostById(String userId, String title) {
        int uId = Integer.valueOf(setExpectedStringValueFromSessionVariable(userId, VAR_USER_ID));
        Response response = updatePost.whenUpdatePostsTitleUsingId(getSessionVariable(VAR_POST_ID), uId, title);
        setSessionVariable(VAR_RESPONSE, response);
    }


    @After("@PostSmoke")
    public void tearDownDeletePost(Scenario scenario) {
        if (!scenario.getName().equals("Deleting post details")) {
            Response response = getPost.getPostById(getSessionVariable(VAR_POST_ID));
            if (response.as(GetPostResponse.class).get_meta().getCode() == 200) {
                deletePost.deletePostUsingId(getSessionVariable(VAR_POST_ID));
            } else {
                System.err.println("The post you want to delete does not exist.");
            }
            deleteUser.deleteUserById(getSessionVariable(VAR_USER_ID));
        }
    }
}