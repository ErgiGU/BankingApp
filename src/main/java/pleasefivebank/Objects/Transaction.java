package pleasefivebank.Objects;

import org.bson.Document;
import org.bson.types.ObjectId;

public final class Transaction {

    private final String date;
    private final String receiver;
    private final String receiverIBAN;
    private final Double quantity;
    private final String concept;
    private String status;

    public Transaction(String date, String receiver, String receiverIban, Double quantity,
                       String concept, String status){
        this.date = date;
        this.receiver = receiver;
        this.receiverIBAN = receiverIban;
        this.quantity = quantity;
        this.concept = concept;
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getReceiverIBAN() { return receiverIBAN; }

    public Double getQuantity() {
        return quantity;
    }

    public String getConcept() {
        return concept;
    }

    public String getStatus() { return status; }


    public void setStatus(String status) { this.status = status; }

    //andreea
    public Document save() {//method to save transaction in collection 4
        Document transaction = new Document("_id", new ObjectId()).append("receiver", this.receiver).
        append("receiver iban", this.receiverIBAN).append("quantity", this.quantity).
                append("date", this.date).append("concept", this.concept).append("status", this.status);
        return transaction;
    }
}
