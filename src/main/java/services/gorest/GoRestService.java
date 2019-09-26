package services.gorest;

import utils.constants.TestConstants;

public class GoRestService {
    public String baseUri;

    public static final String GOREST_API_URI = "/public-api";
    public static final String USERS_ENDPOINT = "/users";

    public GoRestService() {
        this.baseUri = TestConstants.SECURE_PROTOCOL + "://" + TestConstants.GOREST_URI;
    }

    public String getBaseUri() {
        return baseUri;
    }

    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }

}
