package services.gorest.actions.user;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import services.gorest.actions.GoRestActions;
import services.gorest.models.User;
import services.gorest.models.nodes.Links;
import utils.reusable.specifications.ReusableSpecifications;

import static utils.methods.ReusableMethods.generateRandomInt;

public class CreateUser extends GoRestActions {

    private String POST_USER_URL = getBaseUri() + USERS_ENDPOINT;

    @Step("I create a new user")
    public Response createNewUser(User user) {
        Response response = SerenityRest.rest().given().log().all()
                .spec(ReusableSpecifications.authorizedRequestSpec())
                .baseUri(POST_USER_URL)
                .when()
                .body(user)
                .post();
        response.then().log().all();

        return response;
    }

    @Step("When I create an user with email, first name, last name and gender")
    public Response whenCreateNewUser(String email, String firstName, String lastName, String gender) {
        User user = new User(email, firstName, lastName, gender);
        return createNewUser(user);
    }

    @Step("When I create a random user with the mandatory fields")
    public Response whenCreateRandomUserObject() {
        String email = "email" + generateRandomInt(100, 10000) + "@email.com";
        User user = new User(email, "John", "Doe", "male");

        return createNewUser(user);
    }

    @Step("When I create an user with all the details in the fields")
    public Response whenCreateNewUserWithAllFields(String firstName, String lastName, String gender, String dob, String email, String phone, String website, String address, String status) {
        User user = new User(firstName, lastName, gender, dob, email,phone, website, address, status);
        return createNewUser(user);
    }

    @Step("When I create an user with all the mandatory fields and date of birth")
    public Response whenCreateUserWihMandatoryAndDob(String firstName, String lastName, String gender, String dob, String email) {
        User user = new User(firstName, lastName, gender, dob, email);
        return createNewUser(user);
    }
}
