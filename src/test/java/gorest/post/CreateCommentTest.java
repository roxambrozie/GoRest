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
import services.gorest.models.Comment;
import services.gorest.models.responses.GetUserResponse;
import services.gorest.validation.CommonValidations;

import static utils.variables.SessionVariableManager.getSessionVariable;
import static utils.variables.SessionVariableManager.setSessionVariable;
import static utils.variables.SessionVariables.*;

@RunWith(SerenityRunner.class)
@WithTags({
        @WithTag(type = "service", name = "GoRest"),
        @WithTag(type = "type", name = "Smoke"),
        @WithTag(type = "type", name = "Regression"),
        @WithTag(type = "type", name = "Comment")
})
public class CreateCommentTest {

    private Comment comment = new Comment();

    @Steps
    private CreateComment createComment;

    @Steps
    private CommonValidations commonValidations;

    @Steps
    private DeleteComment deleteComment;

    @Before
    public void createPrereq() {
        comment.setPost_id(21);
        comment.setName("Isaac Newton");
        comment.setEmail("email213@email.com");
        comment.setBody("This post is amazing.");
    }

    @Test
    public void createCommentTest() {
        Response response = createComment.createNewComment(comment);
        setSessionVariable(VAR_RESPONSE, response);
        setSessionVariable(VAR_COMMENT_ID, response.as(GetUserResponse.class).getResult().getId());
        commonValidations.validateResponseStatusCode(response, 201);
    }

    @After
    public void tearDown() {
        deleteComment.deleteCommentUsingId(getSessionVariable(VAR_COMMENT_ID));
    }
}
