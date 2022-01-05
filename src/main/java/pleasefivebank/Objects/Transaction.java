package pleasefivebank.Objects;

import org.bson.Document;
import org.bson.types.ObjectId;
import pleasefivebank.Mongo;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static pleasefivebank.EntryPage.EntryPageController.user;

public class Transaction {

    private String senderIBAN;
    private String date;
    private final String receiverName;
    private final String receiverIBAN;
    private final String quantity;
    private final String concept;
    private String status;


    //Carlotta and Juan
    //special constructor that saves the created transaction object to the database and JSON file
    public Transaction(String receiverName, String receiverIBAN, String quantity, String concept, String status){//in TransactionsController we set the date and status
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        this.date = date;
        this.receiverName = receiverName;
        this.receiverIBAN = receiverIBAN;
        this.quantity = quantity;
        this.concept = concept;
        this.status = status;
        if (receiverIBAN.equals(user.getAccountIBAN())){
            this.status = "requested";
        }
        this.senderIBAN = user.getAccountIBAN();
        toDatabase();
    }
    //Carlotta and juan
    //constructor with a different signature to make transaction objects without storing them in the database
    public Transaction(String receiverName, String receiverIBAN, String quantity, String concept, String status, String empty){//in TransactionsController we set the date and status
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        this.date = date;
        this.receiverName = receiverName;
        this.receiverIBAN = receiverIBAN;
        this.quantity = quantity;
        this.concept = concept;
        this.status = status;
        this.senderIBAN = user.getAccountIBAN();
    }
    //Carlotta and Juan
    //makes the document that we will store
    public Document toDocument(){
        Document doc = new Document("_id", new ObjectId()).append("date",this.date).append("senderIBAN",senderIBAN)
                .append("receiverIBAN",receiverIBAN).append("receiverName",receiverName).append("quantity",quantity)
                .append("concept",concept).append("status",status);
        return doc;
    }
    //Carlotta and Juan
    public void toDatabase(){
        Mongo.coll3.insertOne(toDocument());
        toFile();
    }
    //Carlotta and Juan
    //writes into a json file
    public void toFile(){
        try {
            FileWriter writer = new FileWriter("Transactions.json", true);
            writer.write(toDocument().toJson() + System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //method to showcase someone's information in the userHomePage
    public String getOtherPersonsName() {
        if (senderIBAN.equals(user.getAccountIBAN())){
            return receiverName;
        }
        else{
            return Mongo.getDocumentWithIBAN(senderIBAN).get("user name").toString();
        }
    }

    public String getDate() {
        return date;
    }

    public String getReceiverIBAN() { return receiverIBAN; }

    public String getQuantity() {
        return quantity;
    }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public void setDate(String date) { this.date = date; }
}
