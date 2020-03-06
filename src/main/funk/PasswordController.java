package funk;

import java.util.Random;

public class PasswordController {

    static Random random = new Random();

    public final String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public final String lowerCase = "abcdefghijklmnopqrstuvwxyz";
    public final String cifre = "0123456789";
    public final String symbols = ".-_+!?=";
    private int psMin = 6;
    private int psMax = 51;

    public String passwordGen() {
        String output = "";
        String characters = upperCase+lowerCase+cifre+symbols;
        int genPwLength = 8;

        for (int i = 0; i < genPwLength ; i++) {
            int index = random.nextInt(characters.length());
            output += characters.charAt(index);
        }
        return output;
    }


    public boolean isPasswordLegal(String username, String password){

        boolean legalNormal = false;
        boolean legalCaps = false;
        boolean legalSpecial = false;
        boolean legalNumber = false;
        boolean legalName = false;
        boolean legalLength = false;

        for (int i = 0; i< password.length(); i++){

            if(password.charAt(i) <91 && password.charAt(i) >64|| password.charAt(i) == 143 ||password.charAt(i) == 146){
                legalCaps = true;

            }if(password.charAt(i) <123 && password.charAt(i) >96 || password.charAt(i) == 134 ||password.charAt(i) == 145){
                legalNormal = true;
            }
            if((password.charAt(i) <48 && password.charAt(i) >32) || (password.charAt(i) <65 && password.charAt(i) >57) ||(password.charAt(i) <127 && password.charAt(i) >122) ){
                legalSpecial = true;
            }
            if(password.charAt(i) <58 && password.charAt(i) >47){
                legalNumber = true;
            }
            if(!password.contains(username)){
                legalName = true;
            }
            if(password.length() > psMin && password.length() < psMax){
                legalLength = true;
            }

        }
        return legalCaps && legalNormal && legalSpecial && legalNumber && legalName && legalLength;
    }

}




