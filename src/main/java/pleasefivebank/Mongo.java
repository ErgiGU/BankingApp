package pleasefivebank;

import com.mongodb.Block;
import com.mongodb.MongoWriteException;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.model.Projections;
import javafx.scene.control.Label;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Base64;
import java.util.function.Consumer;
import java.util.logging.Logger;
import java.util.logging.Level;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;

public final class Mongo {//marked as final because it is a utility class and it cannot be instantiated
    public static com.mongodb.client.MongoClient client;
    public static MongoDatabase db;
    public static MongoCollection<Document> coll;
    public static MongoCollection<Document> coll2;
    public static MongoCollection<Document> coll3;
    public static MongoCollection<Document> coll4;

    private Mongo(){//private constructor to prevent from instantiating
    }

    public static void mongo() throws Exception {
        Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
        mongoLogger.setLevel(Level.SEVERE);

        client = MongoClients.create
                ("mongodb+srv://adminadmin:wewanta5@please5.wavpm.mongodb.net/please5?retryWrites=true&w=majority");

        db = client.getDatabase("please5");
        coll = db.getCollection("Input_Data");
        coll2 = db.getCollection("Accounts");//access accounts faster
        coll3 = db.getCollection("Transactions");//store transactions safely
        coll4 = db.getCollection("Reviews");//store and access reviews

        try {

            //drop previous import
            coll.drop();

            //Bulk Approach:
            int count = 0;
            int batch = 100;
            List<InsertOneModel<Document>> docs = new ArrayList<>();

            try (BufferedReader br = new BufferedReader(new FileReader("UserDB.json"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    docs.add(new InsertOneModel<>(Document.parse(line)));
                    count++;
                    if (count == batch) {
                        coll.bulkWrite(docs, new BulkWriteOptions().ordered(false));
                        docs.clear();
                        count = 0;
                    }
                }
            }

            if (count > 0) {
                BulkWriteResult bulkWriteResult = coll.bulkWrite(docs, new BulkWriteOptions().ordered(false));
                System.out.println("Inserted" + bulkWriteResult);
            }

        } catch (MongoWriteException e) {
            System.out.println("There is another session open on another port or the server could be down");
        }
    }

    //Ergi && Andreea
    public static boolean existsInDatabase(String itemToFind, String databaseVariable,
                                           Label inputLabel, String validationText) {
        String validationString = null;
        boolean exists = false;
        Document filter = new Document(databaseVariable, itemToFind);
        Document doc = coll.find(filter).first();
        if (doc != null) {
            exists = true;
            validationString = validationText;
        }
        inputLabel.setText(validationString);
        return exists;
    }

    //andreea
    public static void updatePassword(String newPass, String username ) {//encrypted username and new pass
        coll.findOneAndUpdate(eq("user name", username),
                new Document("$set", new Document("password", newPass)));
    }

    //andreea
    public static boolean isAssociatedEmail(String email) {
        Document filter = new Document("email", email);
        Document doc = coll.find(filter).first();
        return doc != null;
    }

    //andreea
    public static boolean isUser(String username) {
        Document filter = new Document("user name", username);
        Document doc = coll.find(filter).first();
        return doc != null;
    }

    //linus
    public static String encrypt(String string){
        //logic to encrypt here
        String encryptedString = Base64.getEncoder().encodeToString(string.getBytes());
        return encryptedString;
    }

    //andreea
    public static boolean isValidLogin(String newUser, String newPass){
        Document doc = coll.find(and(eq("user name",newUser),
                eq("password", newPass))).first();
        return doc != null;
    }

    //andreea
    public static Object extractKey(String newUser, String newPass){//encrypted user & pass
        Document doc = coll.find(and(eq("user name",newUser),
                eq("password", newPass))).first();
        return doc.get("key");
    }

    //andreea
    public static void deleteUser(String username, String email){//we don't delete transactions
        Object key = coll.find(eq("email", email)).first().get("_id");
        coll.findOneAndDelete(eq("email", email));// delete user document
        coll.findOneAndDelete(eq("user name", username));// delete
        coll2.findOneAndDelete(eq("_id", key));//delete account
    }

    //andreea
    public static void activity(Object key, int count){//prints the last count number of transactions
        Bson projection = Projections.fields(Projections.include("sent",
                        "quantity", "receiver", "concept"), Projections.excludeId());
        //look for these fields´ names in the user document with the given id in the database
        coll2.find(eq("_id", key)).projection(projection).sort(new Document
                ("_id",-1)).limit(count).forEach((Consumer<? super Document>) doc ->
                System.out.println(doc.toJson()));
    }

    //andreea
    public static void deleteLast(){//if you added a document by mistake
        Document doc = coll.find().sort(new Document("_id",-1)).limit(1).first();
        coll.deleteOne(doc);//delete last user
        doc = coll.find().sort(new Document("_id",-1)).limit(1).first();
        coll.deleteOne(doc);//delete login credentials
        doc = coll2.find().sort(new Document("_id",-1)).limit(1).first();
        coll2.deleteOne(doc);//delete account
        //doesn't delete it from the file, nothing will..
    }
}

