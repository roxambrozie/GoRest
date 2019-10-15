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
import services.gorest.models.responses.GetAlbumResponse;
import services.gorest.models.responses.GetUserResponse;
import services.gorest.validation.CommonValidations;

@RunWith(SerenityRunner.class)
@WithTags({
        @WithTag(type = "service", name = "GoRest"),
        @WithTag(type = "type", name = "Smoke"),
        @WithTag(type = "type", name = "Regression"),
        @WithTag(type = "type", name = "Album")
})
public class CreateAlbumTest {

    private Album album = new Album();
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
        Response userResponse = createUser.whenCreateRandomUserObject();
        userId = userResponse.as(GetUserResponse.class).getResult().getId();
        album.setUserId(Integer.parseInt(userId));
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
        deleteUser.deleteUserById(userId);
    }
}
