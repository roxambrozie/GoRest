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
import services.gorest.actions.comment.CreateComment;
import services.gorest.actions.comment.DeleteComment;
import services.gorest.actions.comment.GetComment;
import services.gorest.actions.post.CreatePost;
import services.gorest.actions.post.DeletePost;
import services.gorest.actions.user.CreateUser;
import services.gorest.actions.user.DeleteUser;
import services.gorest.models.Comment;
import services.gorest.models.Post;
import services.gorest.models.responses.GetCommentResponse;
import services.gorest.models.responses.GetPostResponse;
import services.gorest.models.responses.GetUserResponse;
import services.gorest.validation.CommonValidations;
import utils.constants.TestConstants;
import utils.methods.JSONUtils;

import static utils.constants.TestConstants.PATH_TO_CREATE_USER_PAYLOAD;
import static utils.constants.TestConstants.PATH_TO_EXISTING_POST;

@RunWith(SerenityRunner.class)
@WithTags({
        @WithTag(type = "service", name = "GoRest"),
        @WithTag(type = "type", name = "Smoke"),
        @WithTag(type = "type", name = "Regression"),
        @WithTag(type = "type", name = "Comment")
})
public class CreateCommentTest {

    private Comment comment = new Comment();
    private String commentId;
    private String userId;
    private String postId;

    @Steps
    private CreateUser createUser;

    @Steps
    private CreatePost createPost;

    @Steps
    private CommonValidations commonValidations;

    @Steps
    private CreateComment createComment;

    @Steps
    private GetComment getComment;

    @Steps
    private DeleteComment deleteComment;

    @Steps
    private DeletePost deletePost;

    @Steps
    private DeleteUser deleteUser;


    @Before
    public void createPrereq() {

        if (TestConstants.CREATE_NEW_USER_FLAG) {
            GetUserResponse user = JSONUtils.createPojoFromJSON(PATH_TO_CREATE_USER_PAYLOAD, GetUserResponse.class);
            Response userResponse = createUser.createNewUser(user.getResult());
            userId = userResponse.as(GetUserResponse.class).getResult().getId();
            Response postResponse = createPost.whenCreateNewPost(Integer.parseInt(userId), "Title", "Body");
            postId = postResponse.as(GetPostResponse.class).getResult().getId();
        } else {
            Post post = JSONUtils.createPojoFromJSON(PATH_TO_EXISTING_POST, Post.class);
            postId = post.getId();
        }
        comment.setPostId(postId);
        comment.setName("Isaac Newton");
        comment.setEmail("email213@email.com");
        comment.setBody("This post is amazing.");
    }

    @Test
    public void createCommentTest() {
        Response response = createComment.createNewComment(comment);
        commentId = response.as(GetCommentResponse.class).getResult().getId();
        commonValidations.validateResponseStatusCode(response, 201);
    }

    @After
    public void tearDown() {
        Response response = getComment.getCommentById(commentId);
        commonValidations.validateResponseStatusCode(response, 200);
        deleteComment.deleteCommentUsingId(commentId);
        if (TestConstants.CREATE_NEW_USER_FLAG) {
            deletePost.deletePostUsingId(postId);
            deleteUser.deleteUserById(userId);
        }
    }
}
