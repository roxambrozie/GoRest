package services.gorest.stepdefinition;


import cucumber.api.java.en.When;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import services.gorest.actions.post.CreatePost;
import services.gorest.actions.post.DeletePost;
import services.gorest.actions.post.GetPost;
import services.gorest.actions.post.UpdatePost;
import services.gorest.models.responses.GetUserResponse;

import static utils.variables.SessionVariableManager.getSessionVariable;
import static utils.variables.SessionVariableManager.setSessionVariable;
import static utils.variables.SessionVariables.VAR_POST_ID;
import static utils.variables.SessionVariables.VAR_RESPONSE;

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
        setSessionVariable(VAR_POST_ID, response.as(GetUserResponse.class).getResult().getId());
        setSessionVariable(VAR_RESPONSE, response);
    }

    @When("^I retrieve a single post based on id$")
    public void whenGetPostById() {
        Response response = getPost.getCreatedPost(getSessionVariable(VAR_RESPONSE));
        setSessionVariable(VAR_RESPONSE, response);
    }

    @When("^I delete a single post based on id$")
    public void whenDeletePostById() {
        Response response = deletePost.deleteCreatedPost(getSessionVariable(VAR_RESPONSE));
        setSessionVariable(VAR_RESPONSE, response);
    }

    @When("^I update a single post using my user id (.*) with the new title (.*)$")
    public void whenUpdatePostById(int userId, String title) {
        Response response = updatePost.whenUpdatePostsTitleUsingId(getSessionVariable(VAR_RESPONSE), userId, title);
        setSessionVariable(VAR_RESPONSE, response);
    }
}
