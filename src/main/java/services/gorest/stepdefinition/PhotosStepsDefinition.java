package services.gorest.stepdefinition;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import services.gorest.actions.album.CreateAlbum;
import services.gorest.actions.album.DeleteAlbum;
import services.gorest.actions.photo.CreatePhoto;
import services.gorest.actions.photo.DeletePhoto;
import services.gorest.actions.photo.GetPhoto;
import services.gorest.actions.photo.UpdatePhoto;
import services.gorest.actions.user.CreateUser;
import services.gorest.actions.user.DeleteUser;
import services.gorest.models.Album;
import services.gorest.models.responses.GetAlbumResponse;
import services.gorest.models.responses.GetPhotoResponse;
import services.gorest.models.responses.GetUserResponse;
import utils.constants.TestConstants;
import utils.methods.JSONUtils;

import static utils.constants.TestConstants.*;
import static utils.methods.ReusableMethods.generateRandomInt;
import static utils.methods.ReusableMethods.replaceExpectedWithVariable;
import static utils.variables.SessionVariableManager.getSessionVariable;
import static utils.variables.SessionVariableManager.setSessionVariable;
import static utils.variables.SessionVariables.*;

public class PhotosStepsDefinition {

    @Steps
    private CreateUser createUser;

    @Steps
    private CreateAlbum createAlbum;

    @Steps
    private CreatePhoto createPhoto;

    @Steps
    private GetPhoto getPhoto;

    @Steps
    private DeletePhoto deletePhoto;

    @Steps
    private UpdatePhoto updatePhoto;

    @Steps
    private DeleteAlbum deleteAlbum;

    @Steps
    private DeleteUser deleteUser;

    @When("^I prepare my prerequisites for adding a photo$")
    public void whenCreatePrerequisitesForPhotos() {

        if (TestConstants.CREATE_NEW_USER_FLAG) {
            GetUserResponse user = JSONUtils.createPojoFromJSON(PATH_TO_CREATE_USER_PAYLOAD, GetUserResponse.class);
            user.getResult().setEmail(generateRandomInt(100,100000) + "@email.com");
            Response userResponse = createUser.createNewUser(user.getResult());
            setSessionVariable(VAR_USER_ID, userResponse.as(GetUserResponse.class).getResult().getId());
            GetAlbumResponse album = JSONUtils.createPojoFromJSON(PATH_TO_CREATE_ALBUM_PAYLOAD, GetAlbumResponse.class);
            Response albumResponse = createAlbum.createNewAlbum(album.getResult());
            setSessionVariable(VAR_ALBUM_ID, albumResponse.as(GetAlbumResponse.class).getResult().getId());
        } else {
            Album album = JSONUtils.createPojoFromJSON(PATH_TO_EXISTING_ALBUM, Album.class);
            setSessionVariable(VAR_ALBUM_ID, album.getId());
        }
    }

    @When("^I add a photo for the album with the id (.*), I provide the title (.*), url (.*) and the following thumbnail: (.*)$")
    public void whenCreatePhoto(String albumId, String title, String url, String thumbnail) {
        replaceExpectedWithVariable(albumId, VAR_ALBUM_ID);
        Response response = createPhoto.whenCreateNewPhoto(getSessionVariable(VAR_ALBUM_ID), title, url, thumbnail);
        GetPhotoResponse getPhotoResponse = response.as(GetPhotoResponse.class);
        setSessionVariable(VAR_RESPONSE, response);
        if (getSessionVariable(VAR_RESPONSE) != null) {
            setSessionVariable(VAR_PHOTO_ID, getPhotoResponse.getResult().getId());
        }
    }

    @When("^I retrieve a single photo based on the id: (.*)$")
    public void whenGetPhotoById(String photoId) {
        replaceExpectedWithVariable(photoId, VAR_PHOTO_ID);
        Response response = getPhoto.getPhotoById(getSessionVariable(VAR_PHOTO_ID));
        setSessionVariable(VAR_RESPONSE, response);
    }

    @When("^I delete a single photo based on the id: (.*)$")
    public void whenDeletePhotoById(String photoId) {
        replaceExpectedWithVariable(photoId, VAR_PHOTO_ID);
        Response response = deletePhoto.deletePhotoUsingId(getSessionVariable(VAR_PHOTO_ID));
        setSessionVariable(VAR_RESPONSE, response);
    }

    @When("^I update a single photo based on the id: (.*), from the album with id (.*) with the new title (.*), url (.*) and thumbnail: (.*)$")
    public void whenUpdatePostById(String photoId, String albumId, String title, String url, String thumbnail) {
        replaceExpectedWithVariable(photoId, VAR_PHOTO_ID);
        replaceExpectedWithVariable(albumId, VAR_ALBUM_ID);
        Response response = updatePhoto.whenUpdatePhotoDetails(getSessionVariable(VAR_PHOTO_ID), getSessionVariable(VAR_ALBUM_ID), title, url, thumbnail);
        setSessionVariable(VAR_RESPONSE, response);
    }

    @After("@PhotoSmoke")
    public void tearDownDeletePhoto(Scenario scenario) {
        if (!scenario.getName().equals("Deleting photo details")) {
            Response response = getPhoto.getPhotoById(getSessionVariable(VAR_PHOTO_ID));
            if (response.as(GetPhotoResponse.class).getMeta().getCode() == 200) {
                deletePhoto.deletePhotoUsingId(getSessionVariable(VAR_PHOTO_ID));

            } else {
                System.err.println("The photo you want to delete does not exist.");
            }
        }
        if (TestConstants.CREATE_NEW_USER_FLAG) {
            deleteAlbum.deleteAlbumUsingId(getSessionVariable(VAR_ALBUM_ID));
            deleteUser.deleteUserById(getSessionVariable(VAR_USER_ID));
        }
    }
}
