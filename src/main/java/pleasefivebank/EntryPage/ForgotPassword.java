package pleasefivebank.EntryPage;

import org.bson.Document;
import pleasefivebank.Mongo;

import static com.mongodb.client.model.Filters.*;

public class ForgotPassword{
    private String username;
    private String password;
    private String email;

    public ForgotPassword(){//in ForgotPasswordController we create an object and then set the attributes values
        this.username = "";
        this.password = "";
        this.email = "";
    }

    public void setUsername(String username) { this.username = username; }

    public void setPassword(String password) { this.password = password; }

    public void setEmail(String email) { this.email = email; }

    //juan && andreea
    public void reset(){
        String encrPass = Mongo.encrypt(password);
        updatePassword(encrPass, email);
    }

    //andreea && Ergi
    public void updatePassword(String newPass, String givenUsername) {
        Mongo.coll.findOneAndUpdate(eq("user name", givenUsername),
                new Document("$set", new Document("password", Mongo.encrypt(newPass))));
        Mongo.updateJson();
    }
}
