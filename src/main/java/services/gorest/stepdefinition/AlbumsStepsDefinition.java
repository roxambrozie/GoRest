package services.gorest.stepdefinition;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import services.gorest.actions.album.CreateAlbum;
import services.gorest.actions.album.DeleteAlbum;
import services.gorest.actions.album.GetAlbum;
import services.gorest.actions.album.UpdateAlbum;
import services.gorest.actions.user.CreateUser;
import services.gorest.actions.user.DeleteUser;
import services.gorest.models.responses.GetAlbumResponse;
import services.gorest.models.responses.GetUserResponse;
import utils.methods.ReusableMethods;

import static utils.methods.ReusableMethods.replaceExpectedWithVariable;
import static utils.variables.SessionVariableManager.getSessionVariable;
import static utils.variables.SessionVariableManager.setSessionVariable;
import static utils.variables.SessionVariables.*;

public class AlbumsStepsDefinition {

    @Steps
    private CreateUser createUser;

    @Steps
    private CreateAlbum createAlbum;

    @Steps
    private GetAlbum getAlbum;

    @Steps
    private UpdateAlbum updateAlbum;

    @Steps
    private DeleteAlbum deleteAlbum;

    @Steps
    private DeleteUser deleteUser;

    @Steps
    private ReusableMethods reusableMethods;

    @Before("@AlbumSmoke")
    public void createPrereq() {
        Response response = createUser.whenCreateRandomUserObject();
        setSessionVariable(VAR_USER_ID, response.as(GetUserResponse.class).getResult().getId());
    }

    @When("^I create a new album with my user id (.*), I provide the title (.*)$")
    public void whenCreateAlbum(String userId, String title) {
        int uId = Integer.parseInt(replaceExpectedWithVariable(userId, VAR_USER_ID));
        Response response = createAlbum.whenCreateNewAlbum(uId, title);
        GetAlbumResponse getAlbumResponse = response.as(GetAlbumResponse.class);
        setSessionVariable(VAR_RESPONSE, response);
        if (getSessionVariable(VAR_RESPONSE) != null) {
            setSessionVariable(VAR_ALBUM_ID, getAlbumResponse.getResult().getId());
        }
    }

    @When("^I retrieve a single album based on the id: (.*)$")
    public void whenGetAlbumById(String albumId) {
        replaceExpectedWithVariable(albumId, VAR_ALBUM_ID);
        Response response = getAlbum.getAlbumById(getSessionVariable(VAR_ALBUM_ID));
        setSessionVariable(VAR_RESPONSE, response);
    }

    @When("^I delete a single album based on the id: (.*)$")
    public void whenDeleteAlbumById(String albumId) {
        replaceExpectedWithVariable(albumId, VAR_ALBUM_ID);
        Response response = deleteAlbum.deleteAlbumUsingId(getSessionVariable(VAR_ALBUM_ID));
        setSessionVariable(VAR_RESPONSE, response);
    }

    @When("^I update a single album with my user id (.*) and the new title (.*)$")
    public void whenUpdateAlbumById(String userId, String title) {
        int uId = Integer.parseInt(replaceExpectedWithVariable(userId, VAR_USER_ID));
        Response response = updateAlbum.whenUpdateAlbumTitleUsingId(getSessionVariable(VAR_ALBUM_ID), uId, title);
        setSessionVariable(VAR_RESPONSE, response);
    }

    @After("@AlbumSmoke")
    public void tearDownDeletePost(Scenario scenario) {
        if (!scenario.getName().equals("Deleting album details")) {
            Response response = getAlbum.getAlbumById(getSessionVariable(VAR_ALBUM_ID));
            if (response.as(GetAlbumResponse.class).getMeta().getCode() == 200) {
                deleteAlbum.deleteAlbumUsingId(getSessionVariable(VAR_ALBUM_ID));
            } else {
                System.err.println("The album you want to delete does not exist.");
            }
            deleteUser.deleteUserById(getSessionVariable(VAR_USER_ID));
        }
    }

}
