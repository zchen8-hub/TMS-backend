package CS542.group6.TMS.util;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.UUID;

public class Util {

    public static UUID generate_UUID(){
        return UUID.randomUUID();
    }

    public static String GenerateRandomString(int n){
        byte[] array = new byte[256];
        new Random().nextBytes(array);

        String randomStr = new String(array, StandardCharsets.UTF_8);
        StringBuilder sb = new StringBuilder();
        String alphaNumericStr = randomStr.replaceAll("[^A-Za-z0-9]", "");

        for (int k = 0; k < alphaNumericStr.length(); k++){
            if (Character.isLetter(alphaNumericStr.charAt(k)) && n > 0
                || Character.isDigit(alphaNumericStr.charAt(k)) && n > 0){
                sb.append(alphaNumericStr.charAt(k));
                n--;
            }
        }

        return sb.toString();
    }
}
