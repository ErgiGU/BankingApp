package pleasefivebank.Menus;
import com.mongodb.client.FindIterable;
import javafx.beans.Observable;
import org.bson.Document;
import org.bson.types.ObjectId;
import pleasefivebank.Mongo;
import pleasefivebank.Objects.MailBot;
import pleasefivebank.Objects.PasswordGen;

import javax.mail.MessagingException;
import java.io.IOException;

import static com.mongodb.client.model.Filters.*;

public class ForgotPassword {
    private String username;
    private String password;
    private String newPass;

    //juan + andreea
    public ForgotPassword(String username, String password, String newPass) throws MessagingException, IOException {
        //maybe this shouldn´t be  in the constructor
        String encrPass = Mongo.encrypt(password);
        String encrUser = Mongo.encrypt(username);
        if (Mongo.isValidLogin(encrPass, encrUser)) {
            Object key = Mongo.extractKey(encrUser, encrPass);
            FindIterable<Document> itr = Mongo.coll.find(eq("key", key));
            String email = itr.first().get("email").toString();
            //send bot email with new password
            PasswordGen gen = new PasswordGen();
            MailBot mail = new MailBot();
            String theNewPassword = gen.GeneratePassword();
            mail.setupServerProperties();
            mail.draftEmail(email,"New Password","your new password " + theNewPassword);
            mail.sendEmail();
            updatePassword(newPass, key);
        }
    }

    //andreea
    public void updatePassword(String newPass, Object id) {
        Mongo.coll.findOneAndUpdate(eq("_id", id),
                new Document("$set", new Document("password", newPass)));
    }
}
