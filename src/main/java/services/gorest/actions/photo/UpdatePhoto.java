package services.gorest.actions.photo;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import services.gorest.actions.GoRestActions;
import services.gorest.models.Photo;
import utils.reusable.specifications.ReusableSpecifications;

public class UpdatePhoto extends GoRestActions {

    private String UPDATE_PHOTO_URL = getBaseUri() + PHOTOS_ENDPOINT;

    @Step("When I update a photo based on id {0}")
    public Response whenUpdatePhoto(String id, Photo photo) {
        Response response = SerenityRest.rest().given().log().all()
                .spec(ReusableSpecifications.authorizedRequestSpec())
                .baseUri(UPDATE_PHOTO_URL)
                .pathParam("id", id)
                .body(photo)
                .when()
                .patch("/{id}");
        response.then().log().all();

        return response;
    }

    public Response whenUpdatePhotoDetails(String id, String albumId, String title, String url, String thumbnail) {
        Photo photo = new Photo();
        photo.setAlbumId(albumId);
        photo.setTitle(title);
        photo.setUrl(url);
        photo.setThumbnail(thumbnail);

        return whenUpdatePhoto(id, photo);
    }

}
