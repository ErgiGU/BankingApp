package pleasefivebank.EntryPage;

import java.util.ArrayList;
import java.util.HashMap;

public class EntryPage {
    public int login(String username, String password){
        //encript

        //check for encripted user with same credentials
        String encryptedName = encrypt(username);
        String encryptedPassword = encrypt(password);

        if (userVerified(encryptedName,encryptedPassword)){
            //key will be the user key to access his info in the database
            int key = 1;
            return key;

        }
        return -1;
    }
    public boolean validatePage1(String firstName, String middleName, String lastName, String PersonalID){
        //if(isAlpha(firstName) && isAlpha(lastName) && !isAlpha(personalID))
        return true;

    }
    public boolean register(HashMap userInfo){
        //we first see if user exists

        //if he doesnt we create account


        return false;
    }
    public String encrypt(String string){
        //logic to encrypt here

        //return encripted string
        return string;
    }
    public boolean userVerified(String encriptedUsername, String encriptedPassword){
        //if user is found and password corresponds

        return true;

        //else return false;

    }
    public boolean isAlpha(String name) {
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }
}
