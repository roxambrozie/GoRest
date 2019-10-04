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
import services.gorest.models.Post;
import services.gorest.validation.CommonValidations;

import static utils.variables.SessionVariableManager.getSessionVariable;
import static utils.variables.SessionVariableManager.setSessionVariable;
import static utils.variables.SessionVariables.VAR_RESPONSE;

@RunWith(SerenityRunner.class)
@WithTags({
        @WithTag(type = "service", name = "GoRest"),
        @WithTag(type = "type", name = "Smoke"),
        @WithTag(type = "type", name = "Regression")
})
public class CreatePostTest {

    private Post myPost = new Post();

    @Steps
    private CommonValidations commonValidations;

    @Steps
    private CreatePost createPost;

    @Steps
    private DeletePost deletePost;

    @Before
    public void createPrereq(){
        myPost.setUser_id(133);
        myPost.setTitle("NASA Takes Delivery of First All-Electric Experimental Aircraft");
        myPost.setBody("The first all-electric configuration of NASA’s X-57 Maxwell now is at the agency’s Armstrong Flight Research Center in Edwards, California.");
    }

    @Test
    public void createPostTest(){
        Response response = createPost.createNewPost(myPost);
        setSessionVariable(VAR_RESPONSE, response);
        commonValidations.validateResponseStatusCode(response, 201);
    }

    @After
    public void tearDown() {
        deletePost.deleteCreatedPost(getSessionVariable(VAR_RESPONSE));
    }
}
