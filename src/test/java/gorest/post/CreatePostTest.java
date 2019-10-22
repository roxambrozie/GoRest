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
import services.gorest.actions.user.CreateUser;
import services.gorest.actions.user.DeleteUser;
import services.gorest.models.Post;
import services.gorest.models.User;
import services.gorest.models.responses.GetPostResponse;
import services.gorest.models.responses.GetUserResponse;
import services.gorest.validation.CommonValidations;
import utils.constants.TestConstants;
import utils.methods.JSONUtils;

import java.io.IOException;

import static utils.constants.TestConstants.PATH_TO_CREATE_USER_PAYLOAD;
import static utils.constants.TestConstants.PATH_TO_EXISTING_USER;
import static utils.methods.ReusableMethods.generateRandomInt;

@RunWith(SerenityRunner.class)
@WithTags({
        @WithTag(type = "service", name = "GoRest"),
        @WithTag(type = "type", name = "Smoke"),
        @WithTag(type = "type", name = "Regression"),
        @WithTag(type = "type", name = "Post")
})
public class CreatePostTest {

    private Post myPost = new Post();
    private User user = new User();
    private String postId;
    private String userId;

    @Steps
    private CommonValidations commonValidations;

    @Steps
    private CreateUser createUser;

    @Steps
    private CreatePost createPost;

    @Steps
    private GetPost getPost;

    @Steps
    private DeletePost deletePost;

    @Steps
    private DeleteUser deleteUser;

    @Before
    public void createPrereq() throws IOException {

        if (TestConstants.CREATE_NEW_USER_FLAG) {
            GetUserResponse user = JSONUtils.createPojoFromJSON(PATH_TO_CREATE_USER_PAYLOAD, GetUserResponse.class);
            user.getResult().setEmail(generateRandomInt(100, 100000) + "@email.com");
            Response userResponse = createUser.createNewUser(user.getResult());
            userId = userResponse.as(GetUserResponse.class).getResult().getId();
            myPost.setUserId(Integer.parseInt(userId));
        } else {
            user = JSONUtils.createPojoFromJSON(PATH_TO_EXISTING_USER, User.class);
            myPost.setUserId(Integer.parseInt(user.getId()));
        }

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
        if (TestConstants.CREATE_NEW_USER_FLAG) {
            deleteUser.deleteUserById(userId);
        }
    }
}
