package services.gorest.steps;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import services.gorest.GoRestService;
import utils.reusable.specifications.ReusableSpecifications;

import static services.gorest.GoRestService.USERS_ENDPOINT;

public class GetUserSteps {

    GoRestService goRestService = new GoRestService();
    private String GET_USER_URL = goRestService.getBaseUri() + GoRestService.GOREST_API_URI + USERS_ENDPOINT;

    @Step("I retrieve a single user based on id {0}")
    public Response getUserUsingId(int id) {
        Response response = SerenityRest.rest().given().log().all()
                .spec(ReusableSpecifications.getGenericRequestSpec())
                .baseUri(GET_USER_URL)
                .pathParam("id", id)
                .when()
                .get("/{id}");
        response.then().log().all();

        return response;
    }



}
