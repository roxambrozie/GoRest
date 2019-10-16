package services.gorest.actions.photo;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import services.gorest.actions.GoRestActions;
import utils.reusable.specifications.ReusableSpecifications;

public class DeletePhoto extends GoRestActions {

    private String DELETE_PHOTO_URL = getBaseUri() + PHOTOS_ENDPOINT;

    @Step("I delete a single photo based on id {0}")
    public Response deletePhotoUsingId(String id) {
        Response response = SerenityRest.rest().given().log().all()
                .spec(ReusableSpecifications.authorizedRequestSpec())
                .baseUri(DELETE_PHOTO_URL)
                .pathParam("id", id)
                .when()
                .delete("/{id}");
        response.then().log().all();

        return response;
    }
}
