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
import pleasefivebank.Objects.Loan;
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

    //method to initialize mongo

    public static void mongo() throws Exception {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        ch.qos.logback.classic.Logger rootLogger = loggerContext.getLogger("org.mongodb.driver");
        rootLogger.setLevel(Level.OFF);


        client = MongoClients.create
                ("mongodb+srv://adminadmin:wewanta5@please5.wavpm.mongodb.net/please5?retryWrites=true&w=majority");

        db = client.getDatabase("please5");
        coll = db.getCollection("Input_Data");
        coll2 = db.getCollection("Loans");
        coll3 = db.getCollection("Transactions");//store transactions safely

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

    //methods to get transactions and loans
    //juan and carlotta
    public static void mongoTransactions() throws Exception{
        coll3.drop();
        List<InsertOneModel<Document>> transactionsDocs = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader("Transactions.json"))) {
                String line2;
                while ((line2 = br.readLine()) != null) {
                    transactionsDocs.add(new InsertOneModel<>(Document.parse(line2)));
                    coll3.bulkWrite(transactionsDocs, new BulkWriteOptions().ordered(false));
                    transactionsDocs.clear();
                }
            }
    }

    public static void mongoLoans() throws Exception{
        coll2.drop();
        List<InsertOneModel<Document>> transactionsDocs = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Loans.json"))) {
            String line2;
            while ((line2 = br.readLine()) != null) {
                transactionsDocs.add(new InsertOneModel<>(Document.parse(line2)));
                coll3.bulkWrite(transactionsDocs, new BulkWriteOptions().ordered(false));
                transactionsDocs.clear();
            }
        }
    }


    //methods to look for things in the database
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

    //the following methods are to check if users or information exists
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

    //juan and carlotta
    public static Document getDocumentWithIBAN(String iban){
        Document doc = coll.find(eq("account IBAN",iban)).first();
        return doc;
    }

    //carlotta and juan
    public static ObservableList<Transaction> getAllTransactions(String iban){
        ObservableList<Transaction> actualTransactions = FXCollections.observableArrayList();
        FindIterable<Document> docs = coll3.find(or(eq("receiverIBAN",iban),eq("senderIBAN",iban)));
        Iterator it = docs.iterator();
        while (it.hasNext()) {
            Document currentDoc = (Document) it.next();
            Transaction transaction = new Transaction(currentDoc.get("receiverName").toString(),
                    currentDoc.get("receiverIBAN").toString(),currentDoc.get("quantity").toString(),
                    currentDoc.get("concept").toString(),currentDoc.get("status").toString(), "");
            if (transaction.getReceiverIBAN().equals(iban)) {
                transaction.setStatus("received");
            }
            if (!transaction.getStatus().equals("requested")){
                actualTransactions.add(transaction);
            }
        }
        return actualTransactions;
    }
    public static ObservableList<Transaction> getAllPendingTransactions(String iban){
        ObservableList<Transaction> pendingTransactions = FXCollections.observableArrayList();
        FindIterable<Document> docs = coll3.find(eq("senderIBAN",iban));
        Iterator it = docs.iterator();
        while (it.hasNext()) {
            Document currentDoc = (Document) it.next();
            Transaction transaction = new Transaction(currentDoc.get("receiverName").toString(),
                    currentDoc.get("receiverIBAN").toString(),currentDoc.get("quantity").toString(),
                    currentDoc.get("concept").toString(), currentDoc.get("status").toString(), "");
            if (transaction.getStatus().equals("requested")){
                pendingTransactions.add(transaction);
            }
        }
        return pendingTransactions;
    }

    public static ObservableList<Loan> getAllLoans(String iban){
        ObservableList<Loan> allLoans = FXCollections.observableArrayList();
        FindIterable<Document> docs = coll3.find(eq("accountIBAN",iban));
        System.out.println("1");
        Iterator it = docs.iterator();
        while (it.hasNext()) {
            Document currentDoc = (Document) it.next();
            Loan loan = new Loan(currentDoc.get("amountPerMonth").toString(),
                    currentDoc.get("accountIBAN").toString(),currentDoc.get("loanPeriod").toString(),
                    currentDoc.get("interestRate").toString(), "");
            System.out.println(loan);
            allLoans.add(loan);
        }
        return allLoans;
    }

    //juan and carlotta
    public static void updateTransactionStatus(String id){
        coll3.findOneAndUpdate(eq("_id", new Document("$oid",id)),
                new Document("$set", new Document("status","sent")));
        updateTransactionsJson();
    }
    //juan and carlotta
    public static void updateTransactionsJson(){
        try {
            FileWriter writer1 = new FileWriter("Transactions.json", false);
            FindIterable<Document> iterDoc = coll3.find();
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
    public static ArrayList<Transaction> getFourTransactions(String IBAN){
        ArrayList<Transaction> fourTransactions = new ArrayList<>();
        FindIterable<Document> docs = coll3.find(or(eq("receiverIBAN",IBAN),eq("senderIBAN",IBAN)));
        Iterator it = docs.iterator();
        while (it.hasNext() && fourTransactions.size() < 4) {
            Document currentDoc = (Document) it.next();
            Transaction transaction = new Transaction(currentDoc.get("receiverName").toString(),
                    currentDoc.get("receiverIBAN").toString(),currentDoc.get("quantity").toString(),
                    currentDoc.get("concept").toString(), currentDoc.get("status").toString(), "");
            if (!transaction.getStatus().equals("requested")){
                fourTransactions.add(transaction);
            }
        }
        return fourTransactions;
    }

}

