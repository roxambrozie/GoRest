package services.gorest.steps;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import services.gorest.GoRestService;
import services.gorest.pojo.User;
import services.gorest.pojo.responses.GetUserResponse;
import utils.methods.ReusableMethods;
import utils.reusable.specifications.ReusableSpecifications;

import static services.gorest.GoRestService.USERS_ENDPOINT;

public class DeleteUserSteps {
    GoRestService goRestService = new GoRestService();
    private String DELETE_USER_URL = goRestService.getBaseUri() + GoRestService.GOREST_API_URI + USERS_ENDPOINT;
    User user = new User();

    @Steps
    private PostUserSteps postUserSteps;

    @Steps
    private ReusableMethods reusableMethods;

    @Steps
    private CommonSteps commonSteps;

    @Step("I delete a single user based on id {0}")
    public Response deleteUserUsingId(int id) {
        Response response = SerenityRest.rest().given().log().all()
                .spec(ReusableSpecifications.getGenericRequestSpec())
                .baseUri(DELETE_USER_URL)
                .pathParam("id", id)
                .when()
                .delete("/{id}");
        response.then().log().all();

        return response;
    }

    @Step("I delete a created user based on id")
    public Response deleteCreatedUser() {
        user = commonSteps.createValidUserObject("email" + reusableMethods.generateRandomInt(10, 100) + "@email.com", "UserName", "Last", "male");
        Response response = postUserSteps.createNewUser(user);
        commonSteps.validateResponseStatusCode(response, 201);
        return deleteUserUsingId(response.as(GetUserResponse.class).getResult().getId());
    }
}
