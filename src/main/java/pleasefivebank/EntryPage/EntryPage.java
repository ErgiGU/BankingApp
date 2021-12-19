package pleasefivebank.EntryPage;

import javafx.scene.control.Tooltip;
import javafx.stage.PopupWindow;
import javafx.util.Duration;
import pleasefivebank.Objects.*;
import org.bson.Document;
import pleasefivebank.Mongo;

import java.util.Base64;
import java.util.Map;

public class EntryPage {
    User login;
    Document session;

    //juan && andreea
    public EntryPage(String username, String password) throws Exception{
        //check for document with same encrypted credentials
        String encryptedName = Mongo.encrypt(username);
        String encryptedPassword = Mongo.encrypt(password);

        if (Mongo.isValidLogin(encryptedName,encryptedPassword)) {
            //key will be the user key to access his info in the database
            Object key = Mongo.extractKey(encryptedName, encryptedPassword);
            //find the document wth the user information in the database
            session = Mongo.coll.find(new Document("key", key.toString())).first();
            //create new user object with the information from database
            login = new User(session.get("user name").toString(), session.get("middle name").toString(),
                    session.get("last name").toString(), session.get("address").toString(),
                    session.get("city").toString(), session.get("postal code").toString(),
                    session.get("birth date").toString(), session.get("phone number").toString(),
                    session.get("personnummer").toString(), session.get("email").toString(),
                    session.get("university").toString(), null);
            session.get("account");
            //login.setAccount();
        }
    }

    //andreea
    public void updateAccount(){
        Document save = login.toDocument();
        Mongo.coll.findOneAndUpdate(session, save);
    }

    //juan
    public boolean isAlpha(String name) {
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }
}
