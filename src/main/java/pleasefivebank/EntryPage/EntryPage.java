package pleasefivebank.EntryPage;

import java.util.ArrayList;
import java.util.HashMap;

public class EntryPage {
    public int login(String username, String password){
        //encript

        //check for encripted user with same credentials
        String encriptedName = encript(username);
        String encriptedPassword = encript(password);

        if (userVerified(encriptedName,encriptedPassword)){
            //key will be the user key to access his info in the database
            int key = 1;
            return key;

        }
        return -1;
    }
    public boolean register(HashMap userInfo){
        //we first see if user exists

        //if he doesnt we create account


        return false;
    }
    public String encript(String string){
        //logic to encrypt here

        //return encripted string
        return string;
    }
    public boolean userVerified(String encriptedUsername, String encriptedPassword){
        //if user is found and password corresponds

        return true;

        //else return false;

    }
}
