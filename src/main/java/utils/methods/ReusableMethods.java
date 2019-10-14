package utils.methods;

import utils.variables.SessionVariables;

import java.util.Random;

import static utils.variables.SessionVariableManager.getSessionVariable;

public class ReusableMethods {

    public static int generateRandomInt(int min, int max){
        Random randomId = new Random();
        return randomId.ints(min, (max+1)).findFirst().getAsInt();
    }

    public static String getRandomStringValue(){
        Random random = new Random();
        int randomInt = random.nextInt();
        return Integer.toString(randomInt);
    }

    public static String replaceExpectedWithVariable(String expectedString, SessionVariables sessionVariable) {
        if (expectedString.equalsIgnoreCase("as expected")) {
            expectedString = getSessionVariable(sessionVariable);
        }
        return expectedString;
    }

}
