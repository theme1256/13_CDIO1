package dto;

import java.util.Random;

public class PasswordGen {

    static Random random = new Random();

    public final String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public final String lowerCase = "abcdefghijklmnopqrstuvwxyz";
    public final String cifre = "0123456789";
    public final String symbols = ".-_+!?=";
    public int min = 6;
    public int max = 51;

    public String passwordGen(String character) {
        String output = "";
        int j =random.nextInt(max-min)+min;
        System.out.println(j);
        for (int i = 0; i < j ; i++) {
            int index = random.nextInt(character.length());
            output += character.charAt(index);
        }
        return output;
    }

}

