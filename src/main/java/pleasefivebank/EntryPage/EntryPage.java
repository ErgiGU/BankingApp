package pleasefivebank.EntryPage;

import javafx.scene.control.Tooltip;
import javafx.stage.PopupWindow;
import javafx.util.Duration;
import pleasefivebank.Objects.*;
import org.bson.Document;
import pleasefivebank.Mongo;

import java.util.Base64;

import static com.mongodb.client.model.Filters.eq;

public class EntryPage {


    //juan + andreea
    public boolean login(String username, String password){
        //encrypt
        String encryptedName = Mongo.encrypt(username);
        String encryptedPassword = Mongo.encrypt(password);
        //check for encrypted user with same credentials
        if (Mongo.isValidLogin(encryptedName,encryptedPassword)) {
            //key will be the user key to access their info in the database
            Object id = Mongo.extractKey(encryptedName, encryptedPassword);
            Document user = Mongo.coll.find(eq("_id", id)).first();//locate the user data inside the database
            //user.get(..);
            //
            return true;
        }
        return false;
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
}
