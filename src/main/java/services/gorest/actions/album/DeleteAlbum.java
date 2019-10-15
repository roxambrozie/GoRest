package services.gorest.actions.album;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import services.gorest.actions.GoRestActions;
import utils.reusable.specifications.ReusableSpecifications;

public class DeleteAlbum extends GoRestActions {

    private String DELETE_ALBUM_URL = getBaseUri() + ALBUMS_ENDPOINT;

    @Step("I delete a single album based on id {0}")
    public Response deleteAlbumUsingId(String id) {
        Response response = SerenityRest.rest().given().log().all()
                .spec(ReusableSpecifications.authorizedRequestSpec())
                .baseUri(DELETE_ALBUM_URL)
                .pathParam("id", id)
                .when()
                .delete("/{id}");
        response.then().log().all();

        return response;
    }

}
