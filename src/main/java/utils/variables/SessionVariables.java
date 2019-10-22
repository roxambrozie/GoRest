package utils.variables;

public enum SessionVariables {
    VAR_RESPONSE("response"),
    VAR_USER("user"),
    VAR_USER_ID("userId"),
    VAR_POST_ID("postId"),
    VAR_COMMENT_ID("commentId"),
    VAR_ALBUM_ID("albumId"),
    VAR_PHOTO_ID("photoId"),
    VAR_STATUS_CODE("code");

    private final String value;

    SessionVariables(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static SessionVariables getVar(String value) {
        for (SessionVariables sv : values()) {
            if (sv.getValue().equalsIgnoreCase(value)) {
                return sv;
            }
        }
        return null;
    }
}
