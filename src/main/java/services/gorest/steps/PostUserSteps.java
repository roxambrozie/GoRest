package services.gorest.steps;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import services.gorest.GoRestService;
import services.gorest.pojo.User;
import utils.methods.ReusableMethods;
import utils.reusable.specifications.ReusableSpecifications;

import static services.gorest.GoRestService.USERS_ENDPOINT;

public class PostUserSteps {
    private GoRestService goRestService = new GoRestService();
    private String POST_USER_URL = goRestService.getBaseUri() + GoRestService.GOREST_API_URI + USERS_ENDPOINT;
    private User user = new User();
    private String email = null;

    @Steps
    private ReusableMethods reusableMethods;

    @Steps
    private CommonSteps commonSteps;

    @Step("I create a new user")
    public Response createNewUser(User user) {
        Response response = SerenityRest.rest().given().log().all()
                .spec(ReusableSpecifications.getGenericRequestSpec())
                .baseUri(POST_USER_URL)
                .contentType(ContentType.JSON)
                .when()
                .body(user)
                .post();
        response.then().log().all();

        return response;
    }

    @Step("When I create a new user for the GoRest API with email, first name, last name and gender")
    public Response whenCreateNewUser(String emailTemplate, String firstName, String lastName, String gender) {
        email = reusableMethods.getRandomStringValue() + emailTemplate;
        user = commonSteps.createValidUserObject(email, firstName, lastName, gender);
        return createNewUser(user);
    }
}
