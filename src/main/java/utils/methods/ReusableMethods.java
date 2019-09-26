package utils.methods;

import java.util.Random;

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

}
