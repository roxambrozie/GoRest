package utils.variables;

public enum SessionVariables {
    VAR_RESPONSE("response"),
    VAR_USER("user"),
    VAR_USER_ID("id"),
    VAR_POST_ID("id"),
    VAR_COMMENT_ID("id"),
    VAR_ALBUM_ID("id"),
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
