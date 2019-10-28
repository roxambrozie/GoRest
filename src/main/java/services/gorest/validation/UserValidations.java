package services.gorest.validation;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import services.gorest.models.responses.GetUserResponse;

public class UserValidations {

    @Step("Then I check the name of the created user is {1} {2}")
    public void validateUserName(Response response, String firstName, String lastName) {
        Assert.assertEquals(firstName, response.as(GetUserResponse.class).getResult().getFirstName());
        Assert.assertEquals(lastName, response.as(GetUserResponse.class).getResult().getLastName());
    }

    @Step("Then I check the email of the created user is {1}")
    public void validateUserEmail(Response response, String email) {
        Assert.assertEquals(email, response.as(GetUserResponse.class).getResult().getEmail());
    }

    @Step("Then I check the gender of the created user is {1}")
    public void validateUserGender(Response response, String gender) {
        Assert.assertEquals(gender, response.as(GetUserResponse.class).getResult().getGender());
    }

    @Step("Then I check the date of birth of the created user is {1}")
    public void validateUserDateOfBirth(Response response, String dob) {
        Assert.assertEquals(dob, response.as(GetUserResponse.class).getResult().getDob());
    }

    @Step("Then I check the phone number of the created user is {1}")
    public void validateUserPhone(Response response, String phone) {
        Assert.assertEquals(phone, response.as(GetUserResponse.class).getResult().getPhone());
    }

    @Step("Then I check the website of the created user is {1}")
    public void validateUserWebsite(Response response, String website) {
        Assert.assertEquals(website, response.as(GetUserResponse.class).getResult().getWebsite());
    }

    @Step("Then I check the address of the created user is {1}")
    public void validateUserAddress(Response response, String address) {
        Assert.assertEquals(address, response.as(GetUserResponse.class).getResult().getAddress());
    }

    @Step("Then I check the status of the created user is {1}")
    public void validateUserStatus(Response response, String status) {
        Assert.assertEquals(status, response.as(GetUserResponse.class).getResult().getStatus());
    }


}
