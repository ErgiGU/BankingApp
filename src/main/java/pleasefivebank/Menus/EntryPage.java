package pleasefivebank.Menus;

import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.bson.types.ObjectId;
import pleasefivebank.Mongo;
import pleasefivebank.Objects.User;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

public class EntryPage {

    public static void createUser() {
        /*User user = new User("juan", "ose", "garcia",
                "vasagatan", "Göteborg", "41112", "123456", "31/10/2000",
                "1234 5678", "0010915611", "juanWantsA5@gmail.com",
                "GU University");
    }

    public static void updatePassword(String newPass, Object id) {
        Mongo.coll.findOneAndUpdate(eq("_id", id),
                new Document("$set", new Document("password", newPass)));
    }

    public static Object extractKey(String newUser, String newPass){
        FindIterable<Document> itr = Mongo.coll.find(and(eq("user name",newUser),
                eq("password", newPass)));
        return itr.first().get("_id");*/
    }

    public void loginUser() {

    }

    //we ask the user either to log in or register

    //log in
    //we ask for username and password
    //we hash the password and compare it to our json file

    //if the input is correct we log in
    UserMenu userMenu = new UserMenu();


    //register user
    //we call create bank account method on createbankaccount object
    CreateBankAccount newUser = new CreateBankAccount();


    //update password and send to email
   // ForgotPassword forgotPassword = new ForgotPassword();
}
