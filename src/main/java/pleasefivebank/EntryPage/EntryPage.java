package pleasefivebank.EntryPage;

import pleasefivebank.Objects.*;
import org.bson.Document;
import pleasefivebank.Mongo;

import java.util.Base64;

public class EntryPage {


    //juan
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
    //linus
    public String encrypt(String string){
        //logic to encrypt here
        String encryptedString = Base64.getEncoder().encodeToString(string.getBytes());
        return encryptedString;
    }
    //juan
    public boolean userVerified(String encriptedUsername, String encriptedPassword){
        //if user is found and password corresponds

        return true;

        //else return false;

    }
    //juan
    public boolean isAlpha(String name) {
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }
    //Ergi
    public  boolean UserExists(String SSN){
        Document filter = new Document( "personalID", SSN );
        if(Mongo.coll.find(filter)!=null){
            return true;
        }
        return false;
    }
}
