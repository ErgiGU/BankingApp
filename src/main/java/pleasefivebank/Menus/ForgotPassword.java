package pleasefivebank.Menus;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import  pleasefivebank.EntryPage.*;
import pleasefivebank.Mongo;

import static com.mongodb.client.model.Filters.*;

public class ForgotPassword {
    private String username;
    private String password;
    private String email;
    private Object key;

    //juan + andreea
    public ForgotPassword(String username,String password) {
        String newPass = Mongo.encrypt(password);
        String newUser = Mongo.encrypt(username);
        if (Mongo.isValidLogin(newUser, newPass)) {
            key = Mongo.extractKey(newUser, newPass);
            FindIterable<Document> itr2 = Mongo.coll.find(eq("key", key.toString()));
            email = itr2.first().get("email").toString();
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
