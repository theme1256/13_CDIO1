package dto;

import java.util.Random;

public class PasswordGen {

    static Random random = new Random();

    static final String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static final String lowerCase = "abcdefghijklmnopqrstuvwxyz";
    static final String cifre = "0123456789";
    static final String symbols = ".-_+!?=";
    static int min = 6;
    static int max = 51;

    public static String passwordGen(String character) {
        String output = "";
        int j =random.nextInt(max-min)+min;
        System.out.println(j);
        for (int i = 0; i < j ; i++) {
            int index = random.nextInt(character.length());
            output += character.charAt(index);
        }
        return output;
    }

    public static void main(String[] args) {
        String password = passwordGen( lowerCase+upperCase+cifre+symbols );
        System.out.println(password);
    }
}

