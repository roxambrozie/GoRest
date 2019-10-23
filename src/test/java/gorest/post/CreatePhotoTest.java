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
import services.gorest.actions.photo.CreatePhoto;
import services.gorest.actions.photo.GetPhoto;
import services.gorest.actions.user.CreateUser;
import services.gorest.actions.user.DeleteUser;
import services.gorest.models.Album;
import services.gorest.models.Photo;
import services.gorest.models.responses.GetAlbumResponse;
import services.gorest.models.responses.GetPhotoResponse;
import services.gorest.models.responses.GetUserResponse;
import services.gorest.validation.CommonValidations;
import utils.constants.TestConstants;
import utils.methods.JSONUtils;

import static utils.constants.TestConstants.PATH_TO_CREATE_USER_PAYLOAD;
import static utils.constants.TestConstants.PATH_TO_EXISTING_ALBUM;
import static utils.methods.ReusableMethods.generateRandomInt;

@RunWith(SerenityRunner.class)
@WithTags({
        @WithTag(type = "service", name = "GoRest"),
        @WithTag(type = "type", name = "Smoke"),
        @WithTag(type = "type", name = "Regression"),
        @WithTag(type = "type", name = "Photo")
})
public class CreatePhotoTest {
    private Photo photo = new Photo();
    private String photoId;
    private String userId;
    private String albumId;

    @Steps
    private CreateUser createUser;

    @Steps
    private CreateAlbum createAlbum;

    @Steps
    private CreatePhoto createPhoto;

    @Steps
    private GetPhoto getPhoto;

    @Steps
    private DeleteAlbum deleteAlbum;

    @Steps
    private DeleteUser deleteUser;

    @Steps
    private CommonValidations commonValidations;

    @Before
    public void createPrereq() {

        if (TestConstants.CREATE_NEW_USER_FLAG) {
            GetUserResponse user = JSONUtils.createPojoFromJSON(PATH_TO_CREATE_USER_PAYLOAD, GetUserResponse.class);
            user.getResult().setEmail(generateRandomInt(100, 100000) + "@email.com");
            Response userResponse = createUser.createNewUser(user.getResult());
            userId = userResponse.as(GetUserResponse.class).getResult().getId();
            Response albumResponse = createAlbum.whenCreateNewAlbum(Integer.parseInt(userId), "My Album");
            albumId = albumResponse.as(GetAlbumResponse.class).getResult().getId();
        } else {
            Album album = JSONUtils.createPojoFromJSON(PATH_TO_EXISTING_ALBUM, Album.class);
            albumId = album.getId();
        }
        photo.setAlbumId(albumId);
        photo.setTitle("My photo");
        photo.setUrl("https://lorempixel.com/1024/768/abstract/?73813");
        photo.setThumbnail("https://lorempixel.com/150/150/abstract/?60104");
    }

    @Test
    public void createPhotoTest() {
        Response response = createPhoto.createNewPhoto(photo);
        photoId = response.as(GetPhotoResponse.class).getResult().getId();
        commonValidations.validateResponseStatusCode(response, 201);
    }

    @After
    public void tearDown() {
        Response response = getPhoto.getPhotoById(photoId);
        commonValidations.validateResponseStatusCode(response, 200);
        if (TestConstants.CREATE_NEW_USER_FLAG) {
            deleteAlbum.deleteAlbumUsingId(albumId);
            deleteUser.deleteUserById(userId);
        }
    }
}
