package pleasefivebank.EntryPage;

import javafx.scene.control.Tooltip;
import javafx.stage.PopupWindow;
import javafx.util.Duration;
import pleasefivebank.Objects.*;
import org.bson.Document;
import pleasefivebank.Mongo;

import java.util.Base64;

public class EntryPage {


    //juan + andreea
    public boolean login(String username, String password){
        //encrypt

        //check for encrypted user with same credentials
        String encryptedName = Mongo.encrypt(username);
        String encryptedPassword = Mongo.encrypt(password);

        if (Mongo.isValidLogin(encryptedName,encryptedPassword)) {
            //key will be the user key to access his info in the database
            Mongo.extractKey(encryptedName, encryptedPassword);
        }
        return true;
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
