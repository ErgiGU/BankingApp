package pleasefivebank.EntryPage;

import pleasefivebank.Objects.*;
import org.bson.Document;
import pleasefivebank.Mongo;


public class EntryPage {
    //in EntryPageController we create an object comprised of the
    //following information when the user logs in
    User login;
    Document session;
    String username;
    String encryptedPassword;


    //this method represents how we approached encryption
    //there are two objects per user, first we access the user object to see if
    //the username and the encrypted password match, if they do we access the key
    //in the first object that will allow us to access the second object with the
    //user information to make a user object that will allow us to extract all of
    //the information needed in runtime.
    //juan && andreea
    public EntryPage(String username) {
        //this.encryptedPassword = password;
        //key will be the user key to access his info in the database
        Object key = Mongo.extractKey("user name", username);
        //find the document wth the user information in the database
        session = Mongo.coll.find(new Document("key", key.toString())).first();
        //create new user object with the information from database
        login = new User(session.get("card number").toString(), session.get("expiration date").toString(), session.get("first name").toString(), session.get("middle name").toString(),
                session.get("last name").toString(), session.get("address").toString(),
                session.get("city").toString(), session.get("postal code").toString(),
                session.get("birth date").toString(), session.get("phone number").toString(),
                session.get("personnummer").toString(), session.get("email").toString(),
                session.get("university").toString(), session.get("account number").toString(),
                session.get("account IBAN").toString(), session.get("balance").toString(), session.get("frozen").toString());
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.encryptedPassword = password;
    }

    public User getLogin() {return login;}
}
