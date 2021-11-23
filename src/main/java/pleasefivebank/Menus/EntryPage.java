package pleasefivebank.Menus;

import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.w3c.dom.Document;
import pleasefivebank.Mongo;

public class EntryPage {

    public static void createUser() {
        //User user = new User("juan", "ose", "garcia",
                //"vasagatan", "Göteborg", "41112", 123456, "31/10/2000",
                //"1234 5678", "0010915611", "juanWantsA5@gmail.com");

        Document user1 = new Document("name","Juan").append("middleName","José").
                append("lastName","García");//Document(<field_name>,<field_value>) because Document implements Map(String, object)
        Document user2 = new Document("name","juan").append("middleName","").
                append("lastName","Ahlback");
        Document docID = new Document("_id", "key");
        //Mongo.coll.insertOne(docID);//inserts document with a given ID "key" into the collection coll;
        //Mongo.coll.insertOne(user1);//inserts document user1 into the collection coll;
        //Mongo.coll.updateOne(user1, user2);//upsert document user2 into the collection coll;
        //Mongo.coll.find(doc);//retrieve a document doc from the collection coll;
        Document filter = new Document("Transactions","");//Document(<field_name>,<field_value>) because Document implements Map(String, object)
        FindIterable<Document> cursor = Mongo.coll.find(filter);//filter documents from the collection coll by a certain field name and field value;
        if (cursor == null) {
            //no values found
        }

        for(Document doc : cursor) {
           // System.out.println("2");
        }
        Mongo.coll.deleteOne(user1);//remove document obj from the collection coll;
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
    ForgotPassword forgotPassword = new ForgotPassword();
}
