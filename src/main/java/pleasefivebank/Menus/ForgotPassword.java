package pleasefivebank.Menus;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import pleasefivebank.Mongo;

import static com.mongodb.client.model.Filters.*;

public class ForgotPassword {
    private String username;
    private String password;
    private String newPass;

    //juan + andreea
    public ForgotPassword(String username, String password, String newPass) {
        //maybe this shouldn´t be  in the constructor
        String encrPass = Mongo.encrypt(password);
        String encrUser = Mongo.encrypt(username);
        if (Mongo.isValidLogin(encrPass, encrUser)) {
            Object key = Mongo.extractKey(encrUser, encrPass);
            FindIterable<Document> itr = Mongo.coll.find(eq("key", key.toString()));
            String email = itr.first().get("email").toString();
            //send bot email with new password
            //Bot logic here
            updatePassword(newPass, key);
        }
    }

    //andreea
    public void updatePassword(String newPass, Object id) {
        Mongo.coll.findOneAndUpdate(eq("_id", id),
                new Document("$set", new Document("password", newPass)));
    }
}
