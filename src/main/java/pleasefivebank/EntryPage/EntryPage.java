package pleasefivebank.EntryPage;

import javafx.scene.control.Tooltip;
import javafx.stage.PopupWindow;
import javafx.util.Duration;
import pleasefivebank.Objects.*;
import org.bson.Document;
import pleasefivebank.Mongo;

import java.util.Base64;
import java.util.Map;

public class EntryPage {//in EntryPageController we create an object and then set the attributes values
    User login;
    Document session;
    String username;
    String encryptedPassword;

    //juan && andreea
    public EntryPage(String password, String username){
        this.encryptedPassword = password;
        //key will be the user key to access his info in the database
        Object key = Mongo.extractKey(username);
        //find the document wth the user information in the database
        session = Mongo.coll.find(new Document("key", key.toString())).first();
        //create new user object with the information from database
        login = new User(session.get("first name").toString(), session.get("middle name").toString(),
                session.get("last name").toString(), session.get("address").toString(),
                session.get("city").toString(), session.get("postal code").toString(),
                session.get("birth date").toString(), session.get("phone number").toString(),
                session.get("personnummer").toString(), session.get("email").toString(),
                session.get("university").toString(), null);
        session.get("account");
        //login.setAccount(); must construct the account object with the info from database
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.encryptedPassword= password;
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
