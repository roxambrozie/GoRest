package utils.variables;

import net.serenitybdd.core.*;

public class SessionVariableManager {

    public static <T> void setSessionVariable(SessionVariables variable, T value) {
        Serenity.setSessionVariable(variable).to(value);
    }

    public static <T> T getSessionVariable(SessionVariables variable) {
        return Serenity.sessionVariableCalled(variable);
    }
}
