package services.gorest.actions.album;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import services.gorest.actions.GoRestActions;
import services.gorest.models.Album;
import utils.reusable.specifications.ReusableSpecifications;

public class CreateAlbum extends GoRestActions {

    private String CREATE_ALBUM_URL = getBaseUri() + ALBUMS_ENDPOINT;

    @Step("I create a new album")
    public Response createNewAlbum(Album album) {
        Response response = SerenityRest.rest().given().log().all()
                .spec(ReusableSpecifications.authorizedRequestSpec())
                .baseUri(CREATE_ALBUM_URL)
                .when()
                .body(album)
                .post();
        response.then().log().all();

        return response;
    }

    @Step("When I create an album with the user id {0} and title {1}")
    public Response whenCreateNewAlbum(int userId, String title) {
        Album post = new Album(userId, title);
        return createNewAlbum(post);
    }
}
