package services.gorest.actions.album;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import services.gorest.actions.GoRestActions;
import services.gorest.models.Album;
import utils.reusable.specifications.ReusableSpecifications;

public class UpdateAlbum extends GoRestActions {

    private String UPDATE_ALBUM_URL = getBaseUri() + ALBUMS_ENDPOINT;

    @Step("When I update an album based on id {0}")
    public Response whenUpdateAlbumById(String id, Album album) {
        Response response = SerenityRest.rest().given().log().all()
                .spec(ReusableSpecifications.authorizedRequestSpec())
                .baseUri(UPDATE_ALBUM_URL)
                .pathParam("id", id)
                .body(album)
                .when()
                .patch("/{id}");
        response.then().log().all();

        return response;
    }


    public Response whenUpdateAlbumTitleUsingId(String albumId, int userId, String title) {
        Album album = new Album();
        album.setUserId(userId);
        album.setTitle(title);
        return whenUpdateAlbumById(albumId, album);
    }

}
