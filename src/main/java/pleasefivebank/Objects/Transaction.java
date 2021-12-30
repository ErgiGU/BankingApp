package pleasefivebank.Objects;

import org.bson.Document;
import org.bson.types.ObjectId;

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

    public Transaction(String receiverName, String receiverIBAN, String quantity, String concept){//in TransactionsController we set the date and status
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        this.date = date;
        this.receiverName = receiverName;
        this.receiverIBAN = receiverIBAN;
        this.quantity = quantity;
        this.concept = concept;
        this.status = "send";
        this.senderIBAN = user.getAccountIBAN();
    }

    public String getDate() {
        return date;
    }

    public String getReceiver() {
        return receiverName;
    }

    public String getReceiverIBAN() { return receiverIBAN; }

    public String getQuantity() {
        return quantity;
    }

    public String getConcept() {
        return concept;
    }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public void setDate(String date) { this.date = date; }

    //andreea
    public Document save() {//method to save transaction in collection 4
        Document transaction = new Document("_id", new ObjectId()).append("receiver", this.receiverName).
        append("receiver iban", this.receiverIBAN).append("quantity", this.quantity).
                append("date", this.date).append("concept", this.concept).append("status", this.status);
        return transaction;
    }

    @Override
    public String toString() {
        return "Date: " + date + '\n' +
                "Receiver: " + receiverName + " IBAN: " + receiverIBAN + '\n' +
                "Quantity: " + quantity +
                "Concept: " + concept +
                "Status: " + status + '\n';
    }
}
