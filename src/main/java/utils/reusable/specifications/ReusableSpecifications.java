package utils.reusable.specifications;

import io.restassured.authentication.PreemptiveOAuth2HeaderScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.lessThan;
import static utils.constants.TestConstants.GO_REST_ACCESS_TOKEN;

public class ReusableSpecifications {

    public static RequestSpecification authorizedRequestSpec() {
        RequestSpecBuilder rspec;
        RequestSpecification requestSpecification;
        rspec = new RequestSpecBuilder();
        PreemptiveOAuth2HeaderScheme oAuth2HeaderScheme = new PreemptiveOAuth2HeaderScheme();
        oAuth2HeaderScheme.setAccessToken(GO_REST_ACCESS_TOKEN);
        rspec.setAuth(oAuth2HeaderScheme);
        rspec.setContentType(ContentType.JSON);
        requestSpecification = rspec.build();
        return requestSpecification;
    }

    public static RequestSpecification unAuthorizedRequestSpec() {
        RequestSpecBuilder rspec;
        RequestSpecification requestSpecification;
        rspec = new RequestSpecBuilder();
        rspec.setContentType(ContentType.JSON);
        requestSpecification = rspec.build();
        return requestSpecification;
    }

    public static ResponseSpecification responseSpec() {
        ResponseSpecBuilder respec;
        ResponseSpecification responseSpecification;
        respec = new ResponseSpecBuilder();
        respec.expectHeader("Content-Type", "application/json; charset=UTF-8");
        responseSpecification = respec.build();
        return responseSpecification;
    }
}
