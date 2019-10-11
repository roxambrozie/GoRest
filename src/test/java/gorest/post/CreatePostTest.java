package gorest.post;

import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import services.gorest.actions.post.CreatePost;
import services.gorest.actions.post.DeletePost;
import services.gorest.actions.post.GetPost;
import services.gorest.models.Post;
import services.gorest.models.responses.GetPostResponse;
import services.gorest.validation.CommonValidations;

@RunWith(SerenityRunner.class)
@WithTags({
        @WithTag(type = "service", name = "GoRest"),
        @WithTag(type = "type", name = "Smoke"),
        @WithTag(type = "type", name = "Regression"),
        @WithTag(type = "type", name = "Post")
})
public class CreatePostTest {

    private Post myPost = new Post();
    private String postId;

    @Steps
    private CommonValidations commonValidations;

    @Steps
    private CreatePost createPost;

    @Steps
    private GetPost getPost;

    @Steps
    private DeletePost deletePost;

    @Before
    public void createPrereq() {
        //TODO create a way to switch between adding a new user and adding a post from an existing user
        myPost.setUserId(2202);
        myPost.setTitle("NASA Takes Delivery of First All-Electric Experimental Aircraft");
        myPost.setBody("The first all-electric configuration of NASA’s X-57 Maxwell now is at the agency’s Armstrong Flight Research Center in Edwards, California.");
    }

    @Test
    public void createPostTest() {
        Response response = createPost.createNewPost(myPost);
        commonValidations.validateResponseStatusCode(response, 201);
        postId = response.as(GetPostResponse.class).getResult().getId();
    }

    @After
    public void tearDown() {
        Response response = getPost.getPostById(postId);
        commonValidations.validateResponseStatusCode(response, 200);
        deletePost.deletePostUsingId(postId);
    }
}
