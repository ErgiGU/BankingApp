package pleasefivebank;

import com.mongodb.MongoWriteException;
import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.InsertOneModel;
import org.bson.Document;

import java.util.Base64;
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

    private Mongo(){//private constructor to prevent from instantiating
    }

    public static void mongo() throws Exception {
        Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
        mongoLogger.setLevel(Level.SEVERE);

        client = MongoClients.create
                ("mongodb+srv://adminadmin:wewanta5@please5.wavpm.mongodb.net/please5?retryWrites=true&w=majority");

        db = client.getDatabase("please5");
        coll = db.getCollection("Input_Data");

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

    //andreea
    public static boolean isAssociatedEmail(String email) {
        Document filter = new Document("email", email);
        FindIterable<Document> itr = coll.find(filter);
        return itr.first() != null;
    }

    //andreea
    public static void updatePassword(String newPass, String username ) {
        coll.findOneAndUpdate(eq("user name", username),
                new Document("$set", new Document("password", newPass)));
    }

    //andreea
    public static boolean isUser(String username) {
        Document filter = new Document("user name", username);
        FindIterable<Document> itr = coll.find(filter);
        return itr.first() != null;
    }

    //linus
    public static String encrypt(String string){
        //logic to encrypt here
        String encryptedString = Base64.getEncoder().encodeToString(string.getBytes());
        return encryptedString;
    }

    //andreea
    public static Object extractKey(String newUser, String newPass){
        FindIterable<Document> itr = coll.find(and(eq("user name",newUser),
                eq("password", newPass)));
        return itr.first().get("key");
    }

    //andreea
    public static boolean isValidLogin(String newUser, String newPass){
        FindIterable<Document> itr = coll.find(and(eq("user name",newUser),
                eq("password", newPass)));
        return itr.first() != null;
    }

    //andreea
    public static void deleteUser(String newUser, String email) {
        coll.findOneAndDelete(eq("email", email));
        coll.findOneAndDelete(eq("user name", newUser));
    }

    /*public static Document listAccounts(){
    }*/
}

