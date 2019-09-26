package services.gorest.steps;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import services.gorest.GoRestService;
import services.gorest.pojo.User;
import utils.reusable.specifications.ReusableSpecifications;

import static services.gorest.GoRestService.USERS_ENDPOINT;

public class UpdateUserSteps {
    GoRestService goRestService = new GoRestService();
    private String UPDATE_USER_URL = goRestService.getBaseUri() + GoRestService.GOREST_API_URI + USERS_ENDPOINT;
    private User user = new User();

    @Step("When I update a single user based on id {0}")
    public Response whenUpdateUserUsingId(int id, User user) {
        Response response = SerenityRest.rest().given().log().all()
                .spec(ReusableSpecifications.getGenericRequestSpec())
                .baseUri(UPDATE_USER_URL)
                .pathParam("id", id)
                .body(user)
                .when()
                .patch("/{id}");
        response.then().log().all();

        return response;
    }

    @Step("When I update the last name of a user ")
    public Response whenUpdateUserLastNameUsingId(int id,String last_name){
        user.setLastName(last_name);
        return whenUpdateUserUsingId(id, user);
    }

}
