package funk;

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


    public static boolean isPasswordSafe(String username, String password){

        boolean safeNormal = false;
        boolean safeCaps = false;
        boolean safeSpecial = false;
        boolean safeNumber = false;
        boolean safeName = false;

        for (int i = 0; i< password.length(); i++){

            if(password.charAt(i) <91 && password.charAt(i) >64|| password.charAt(i) == 143 ||password.charAt(i) == 146){
                safeCaps = true;

            }if(password.charAt(i) <123 && password.charAt(i) >96 || password.charAt(i) == 134 ||password.charAt(i) == 145){
                safeNormal = true;
            }
            if((password.charAt(i) <48 && password.charAt(i) >32) || (password.charAt(i) <65 && password.charAt(i) >57) ||(password.charAt(i) <127 && password.charAt(i) >122) ){
                safeSpecial = true;
            }
            if(password.charAt(i) <58 && password.charAt(i) >47){
                safeNumber = true;
            }
            if(!password.contains(username)){
                safeName = true;
            }

        }
        return safeCaps&&safeNormal&&safeSpecial&&safeNumber&&safeName;
    }

}




