package pleasefivebank;

import com.dlsc.formsfx.model.structure.StringField;
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
import com.mongodb.client.model.UpdateOptions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import org.bson.BsonType;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonReader;
import pleasefivebank.Objects.Transaction;
import pleasefivebank.Objects.User;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.logging.Logger;

import java.io.BufferedReader;
import java.io.FileReader;

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
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        ch.qos.logback.classic.Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
        rootLogger.setLevel(Level.OFF);


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


            try (BufferedReader br = new BufferedReader(new FileReader("UserDB2.json"))) {
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

    public static void mongoTransactions() throws Exception{
        int count = 0;
        int batch = 100;
        List<InsertOneModel<Document>> transactionsDocs = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader("Transactions.json"))) {
                String line2;
                while ((line2 = br.readLine()) != null) {
                    transactionsDocs.add(new InsertOneModel<>(Document.parse(line2)));
                    count++;
                    if (count == batch) {
                        coll3.bulkWrite(transactionsDocs, new BulkWriteOptions().ordered(false));
                        transactionsDocs.clear();
                        count = 0;
                    }
                }
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

    //andreea
    public static boolean isAccount(String fullName, String iban) {//display message in the GUI to only use first and last name
        var names = fullName.split(" ");
        Document filter1 = new Document("first name", names[0]);
        Document filter2 = new Document("last name", names[1]);
        Document doc = coll.find(and(eq(filter1),eq(filter2), eq("accountIban", iban))).first();
        return doc != null;
    }
    //juan and carlotta
    public static boolean isValidIBAN(String iban) {//display message in the GUI to only use first and last name
        Document doc = coll.find(eq("account IBAN", iban)).first();
        System.out.println(doc);
        return doc != null;
    }

    //linus
    public static String encrypt(String string){
        //logic to encrypt here
        String encryptedString = Base64.getEncoder().encodeToString(string.getBytes());
        return encryptedString;
    }

    //andreea
    public static boolean isValidLogin(String username, String newPass){
        Document doc = coll.find(and(eq("user name",username),
                eq("password", newPass))).first();
        return doc != null;
    }

    //andreea
    public static Object extractKey(String thingToLookFor,String username){
        Document doc = coll.find(eq(thingToLookFor, username)).first();
        return doc.get("_id");
    }
    //juan and Ergi
    public static Object extractKey2(String thingToLookFor,String username){
        Document doc = coll.find(eq(thingToLookFor, username)).first();
        return doc.get("key");
    }

    //Ergi and juan
    public static Object getUsername(String id, String returnThing){
        Document doc = coll.find(eq("_id",id)).first();
        Object username = doc.get(returnThing);
        return username;
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

    //Lotti
    public static void updateJson(){
        try {
            FileWriter writer1 = new FileWriter("UserDB2.json", false);
            FindIterable<Document> iterDoc = coll.find();
            Iterator it = iterDoc.iterator();
            while (it.hasNext()) {
                Document doc = (Document) it.next();
                writer1.write(doc.toJson() +  System.lineSeparator());
            }
            writer1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //andreea
    public static String formatTime() {//this methods registers the time an object Date is created
        Date time = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(time);
    }

    public static boolean usernameExists(String username) {
        boolean exists = false;
        Document filter = new Document("user name", username);
        Document doc = coll.find(filter).first();
        if (doc != null) {
            exists = true;
        }
        return exists;
    }

    //Ergi
    public static void updateInformation(String dbLabel, String updatedVar, String personalID ) {
        coll.findOneAndUpdate(eq("personnummer", personalID),
                new Document("$set", new Document(dbLabel, updatedVar)));

    }

    public static User getUser(String dbLabel, String value){
        //find the document wth the user information in the database
        Document session = Mongo.coll.find(new Document(dbLabel, value)).first();
        //create new user object with the information from database
        User currentUser = new User(session.get("card number").toString(),session.get("expiration date").toString(),session.get("first name").toString(), session.get("middle name").toString(),
                session.get("last name").toString(), session.get("address").toString(),
                session.get("city").toString(), session.get("postal code").toString(),
                session.get("birth date").toString(), session.get("phone number").toString(),
                session.get("personnummer").toString(), session.get("email").toString(),
                session.get("university").toString(), session.get("account number").toString(),
                session.get("account IBAN").toString(),session.get("balance").toString(),session.get("frozen").toString()
        );
        return currentUser;
    }

    public static Document getDocumentWithIBAN(String iban){
        Document doc = coll.find(eq("account IBAN",iban)).first();
        return doc;
    }

    //carlotta
    public static ObservableList<Transaction> getAllTransactions(String iban){
        ObservableList<Transaction> allTransactions = FXCollections.observableArrayList();
        FindIterable<Document> docs = coll3.find(or(eq("receiverIBAN",iban),eq("senderIBAN",iban)));
        Iterator it = docs.iterator();
        while (it.hasNext()) {
            Document currentDoc = (Document) it.next();
            Transaction transaction = new Transaction(currentDoc.get("receiverName").toString(),currentDoc.get("receiverIBAN").toString(),currentDoc.get("quantity").toString(),currentDoc.get("concept").toString());
            allTransactions.add(transaction);
        }
        return allTransactions;

    }


}

