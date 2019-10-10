package utils.methods;

import utils.variables.SessionVariables;

import java.util.Random;

import static utils.variables.SessionVariableManager.getSessionVariable;
import static utils.variables.SessionVariables.VAR_USER_ID;

public class ReusableMethods {

    public int generateRandomInt(int min, int max){
        Random randomId = new Random();
        return randomId.ints(min, (max+1)).findFirst().getAsInt();
    }

    public String getRandomStringValue(){
        Random random = new Random();
        int randomInt = random.nextInt();
        return Integer.toString(randomInt);
    }

    public String setSessionVariableAsExpected(String expectedString, SessionVariables sessionVariable) {
        if (expectedString.equalsIgnoreCase("as expected")) {
            expectedString = getSessionVariable(sessionVariable);
        }
        return expectedString;
    }

}
