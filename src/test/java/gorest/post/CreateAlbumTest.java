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
import services.gorest.actions.album.CreateAlbum;
import services.gorest.actions.album.DeleteAlbum;
import services.gorest.actions.album.GetAlbum;
import services.gorest.actions.user.CreateUser;
import services.gorest.actions.user.DeleteUser;
import services.gorest.models.Album;
import services.gorest.models.User;
import services.gorest.models.responses.GetAlbumResponse;
import services.gorest.models.responses.GetUserResponse;
import services.gorest.validation.CommonValidations;
import utils.constants.TestConstants;
import utils.methods.JSONUtils;

import static utils.constants.TestConstants.PATH_TO_CREATE_USER_PAYLOAD;
import static utils.constants.TestConstants.PATH_TO_EXISTING_USER;

@RunWith(SerenityRunner.class)
@WithTags({
        @WithTag(type = "service", name = "GoRest"),
        @WithTag(type = "type", name = "Smoke"),
        @WithTag(type = "type", name = "Regression"),
        @WithTag(type = "type", name = "Album")
})
public class CreateAlbumTest {

    private Album album = new Album();
    private User user = new User();
    private String albumId;
    private String userId;

    @Steps
    private CommonValidations commonValidations;

    @Steps
    private CreateUser createUser;

    @Steps
    private CreateAlbum createAlbum;

    @Steps
    private GetAlbum getAlbum;

    @Steps
    private DeleteAlbum deleteAlbum;

    @Steps
    private DeleteUser deleteUser;

    @Before
    public void createPrereq() {

        if (TestConstants.CREATE_NEW_USER_FLAG) {
            GetUserResponse user = JSONUtils.createPojoFromJSON(PATH_TO_CREATE_USER_PAYLOAD, GetUserResponse.class);
            Response userResponse = createUser.createNewUser(user.getResult());
            userId = userResponse.as(GetUserResponse.class).getResult().getId();
            album.setUserId(Integer.parseInt(userId));
        } else {
            user = JSONUtils.createPojoFromJSON(PATH_TO_EXISTING_USER, User.class);
            album.setUserId(Integer.parseInt(user.getId()));
        }
        album.setTitle("Vacation photos");
    }

    @Test
    public void createAlbumTest() {
        Response response = createAlbum.createNewAlbum(album);
        albumId = response.as(GetAlbumResponse.class).getResult().getId();
        commonValidations.validateResponseStatusCode(response, 201);
    }

    @After
    public void tearDown() {
        Response response = getAlbum.getAlbumById(albumId);
        commonValidations.validateResponseStatusCode(response, 200);
        deleteAlbum.deleteAlbumUsingId(albumId);
        if (TestConstants.CREATE_NEW_USER_FLAG) {
            deleteUser.deleteUserById(userId);
        }
    }
}
