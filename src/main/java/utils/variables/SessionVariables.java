package utils.variables;

public enum SessionVariables {
    VAR_RESPONSE("response"),
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
