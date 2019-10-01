package services.gorest.actions;

import utils.constants.TestConstants;

public abstract class GoRestService {
    private String baseUri;

    //resources
    protected static final String USERS_ENDPOINT = "/public-api/users";
    protected static final String POSTS_ENDPOINT = "/public-api/posts";
    protected static final String COMMENTS_ENDPOINT = "/public-api/comments";
    protected static final String ALBUMS_ENDPOINT = "/public-api/albums";
    protected static final String PHOTOS_ENDPOINT = "/public-api/photos";

    protected String getBaseUri() {
        return baseUri;
    }

    private void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }

    GoRestService() {
        setBaseUri(TestConstants.SECURE_PROTOCOL + "://" + TestConstants.GOREST_URI);
    }

}
