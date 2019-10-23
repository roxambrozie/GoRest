package services.gorest.actions.photo;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import services.gorest.actions.GoRestActions;
import utils.reusable.specifications.ReusableSpecifications;

public class GetPhoto extends GoRestActions {

    private String GET_PHOTO_URL = getBaseUri() + PHOTOS_ENDPOINT;

    @Step("I retrieve a single photo based on id {0}")
    public Response getPhotoById(String id) {
        Response response = SerenityRest.rest().given().log().all()
                .spec(ReusableSpecifications.authorizedRequestSpec())
                .baseUri(GET_PHOTO_URL)
                .pathParam("id", id)
                .when()
                .get("/{id}");
        response.then().log().all();

        return response;
    }
}
