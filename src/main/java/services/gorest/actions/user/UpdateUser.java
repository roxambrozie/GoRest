package services.gorest.actions.user;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import services.gorest.actions.GoRestActions;
import services.gorest.models.User;
import utils.reusable.specifications.ReusableSpecifications;

public class UpdateUser extends GoRestActions {


    private String UPDATE_USER_URL = getBaseUri() + USERS_ENDPOINT;

    @Step("When I update a single user based on id {0}")
    public Response whenUpdateUserDetailById(String id, User user) {
        Response response = SerenityRest.rest().given().log().all()
                .spec(ReusableSpecifications.authorizedRequestSpec())
                .baseUri(UPDATE_USER_URL)
                .pathParam("id", id)
                .body(user)
                .when()
                .patch("/{id}");
        response.then().log();

        return response;
    }

    @Step("When I update a single user based on id {0} with all details")
    public Response whenUpdateAllUserDetailsById(String id, User user) {
        Response response = SerenityRest.rest().given().log().all()
                .spec(ReusableSpecifications.authorizedRequestSpec())
                .baseUri(UPDATE_USER_URL)
                .pathParam("id", id)
                .body(user)
                .when()
                .put("/{id}");
        response.then().log();

        return response;
    }

    public Response whenUpdateUsersLastNameUsingId(String id, String lastName) {
        User user = new User();
        user.setLastName(lastName);
        return whenUpdateUserDetailById(id, user);
    }

    public Response whenUpdateAllUserMandatoryDetailsById(String id, String firstName, String lastName, String email, String status, String gender) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setGender(gender);
        user.setStatus(status);

        return whenUpdateAllUserDetailsById(id, user);
    }

    public Response whenUpdateAllUserDetailsById(String id, String firstName, String lastName, String gender, String dob, String email, String phoneNumber, String website, String address, String status) {
        User user = new User(firstName, lastName, gender, dob, email, phoneNumber, website, address, status);

        return whenUpdateAllUserDetailsById(id, user);
    }

    public Response whenUpdateUsersFirstName(String id, String firstName) {
        User user = new User();
        user.setFirstName(firstName);
        return whenUpdateUserDetailById(id, user);
    }

    public Response whenUpdateUsersGender(String id, String gender) {
        User user = new User();
        user.setGender(gender);
        return whenUpdateUserDetailById(id, user);
    }

    public Response whenUpdateUsersDateOfBirth(String id, String dob) {
        User user = new User();
        user.setDob(dob);
        return whenUpdateUserDetailById(id, user);
    }

    public Response whenUpdateUsersEmail(String id, String email) {
        User user = new User();
        user.setEmail(email);
        return whenUpdateUserDetailById(id, user);
    }

    public Response whenUpdateUsersPhone(String id, String phone) {
        User user = new User();
        user.setPhone(phone);
        return whenUpdateUserDetailById(id, user);
    }

    public Response whenUpdateUsersWebsite(String id, String website) {
        User user = new User();
        user.setWebsite(website);
        return whenUpdateUserDetailById(id, user);
    }

    public Response whenUpdateUsersAddress(String id, String address) {
        User user = new User();
        user.setAddress(address);
        return whenUpdateUserDetailById(id, user);
    }

    public Response whenUpdateUsersStatus(String id, String status) {
        User user = new User();
        user.setStatus(status);
        return whenUpdateUserDetailById(id, user);
    }
}