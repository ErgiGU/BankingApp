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
    }

    public static void register(){
        String birthdate = extractBirthdate("0009714102");
        User newUser = new User("Elisa", "", "Ahlback", "Lindholmspiren 4", "41756",
                "Göteborg", "", birthdate, "4600390074", "0009714102",
                "elisagamergirl@protonmail.com", "GU University");
        //We write the user as document
        Document user = newUser.toDocument();
        //we create a document with encrypted credentials and add it to the database
        Document login = new Document("user name", Mongo.encrypt("elisagamergirl")).
                append("password", Mongo.encrypt("gubbins!1234"));
        Mongo.coll.insertOne(login);
        //get the automatically generated id of the document just inserted
        FindIterable<Document> itr = Mongo.coll.find(login);
        String key = itr.first().get("_id").toString();
        //store the id in the key field of the user document and add the user to the database
        Mongo.coll.insertOne(user.append("key", key));
    }

    //andreea + ossian
    public static String extractBirthdate(String personnummer){
        int year;
        String yearString  = personnummer.substring(0,2);
        year = Integer.parseInt(yearString);
        if(year > 22){
            year += 1900;
        } else {
            year += 2000;
        }
        String month = personnummer.substring(2,4);
        String day = personnummer.substring(4,6);
        return year + "/"+ month + "/"+ day;
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
