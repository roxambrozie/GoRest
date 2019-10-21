package services.gorest.actions.album;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import services.gorest.actions.GoRestActions;
import utils.reusable.specifications.ReusableSpecifications;

public class GetAlbum extends GoRestActions {

    private String GET_ALBUM_URL = getBaseUri() + ALBUMS_ENDPOINT;

    @Step("I retrieve a single album based on id {0}")
    public Response getAlbumById(String id) {
        Response response = SerenityRest.rest().given().log().all()
                .spec(ReusableSpecifications.authorizedRequestSpec())
                .baseUri(GET_ALBUM_URL)
                .pathParam("id", id)
                .when()
                .get("/{id}");
        response.then().log().all();

        return response;
    }
}
