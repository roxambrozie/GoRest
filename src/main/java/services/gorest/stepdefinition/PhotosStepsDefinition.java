package services.gorest.stepdefinition;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
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
import services.gorest.models.responses.GetAlbumResponse;
import services.gorest.models.responses.GetCommentResponse;
import services.gorest.models.responses.GetPhotoResponse;
import services.gorest.models.responses.GetUserResponse;

import static utils.methods.ReusableMethods.replaceExpectedWithVariable;
import static utils.variables.SessionVariableManager.getSessionVariable;
import static utils.variables.SessionVariableManager.setSessionVariable;
import static utils.variables.SessionVariables.*;

public class PhotosStepsDefinition {
    private String userId;

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

    @Before("@PhotoSmoke")
    public void createPrereq() {
        Response userResponse = createUser.whenCreateRandomUserObject();
        userId = userResponse.as(GetUserResponse.class).getResult().getId();
        Response albumResponse = createAlbum.whenCreateNewAlbum(Integer.parseInt(userId), "My album");
        setSessionVariable(VAR_ALBUM_ID, albumResponse.as(GetAlbumResponse.class).getResult().getId());
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
                deleteAlbum.deleteAlbumUsingId(getSessionVariable(VAR_ALBUM_ID));

            } else {
                System.err.println("The photo you want to delete does not exist.");
            }
            deleteUser.deleteUserById(userId);
        }
    }
}
