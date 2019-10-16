package services.gorest.actions.photo;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import services.gorest.actions.GoRestActions;
import services.gorest.models.Photo;
import utils.reusable.specifications.ReusableSpecifications;

public class CreatePhoto extends GoRestActions {

    private String CREATE_PHOTO_URL = getBaseUri() + PHOTOS_ENDPOINT;

    @Step("I add a new photo")
    public Response createNewPhoto(Photo photo) {
        Response response = SerenityRest.rest().given().log().all()
                .spec(ReusableSpecifications.authorizedRequestSpec())
                .baseUri(CREATE_PHOTO_URL)
                .when()
                .body(photo)
                .post();
        response.then().log().all();

        return response;
    }

    @Step("When I add a photo to a post with the album id {0}, title {1}, url and thumbnail")
    public Response whenCreateNewPhoto(String albumId, String title, String url, String thumbnail) {
        Photo photo = new Photo(albumId, title, url, thumbnail);
        return createNewPhoto(photo);
    }
}
