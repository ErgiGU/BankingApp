package pleasefivebank.Objects;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.w3c.dom.events.DocumentEvent;
import pleasefivebank.Mongo;

import java.util.ArrayList;
import java.util.Date;

import static com.mongodb.client.model.Filters.eq;
import static java.util.Arrays.asList;

public class User {
    //variables storing basic user info + arraylist of his transactions + arraylist of his accounts
    private String firstName;
    private final String birthdate;
    private String phoneNumber;
    private final String personnummer;
    private String email;
    private String address;
    private String city;
    private String postalCode;
    private String middleName;
    private String lastName;
    private String university;
    private Document doc;



    protected String balance;
    protected int rewardPoints;
    protected String frozen;
    protected final String accountNr;
    protected final String accountIBAN;
    protected ArrayList<Transaction> activity = new ArrayList<>();
    protected ArrayList<Transaction> pending = new ArrayList<>();

    public User(String name, String middleName, String lastName, String address, String city, String postalCode,
                String birthDate, String phoneNumber, String personNummer, String email, String university, String accountNr, String accountIBAN, String balance, String frozen) {
        this.firstName = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthdate = birthDate;
        this.phoneNumber = phoneNumber;
        this.personnummer = personNummer;
        this.email = email;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.university = university;


        this.accountNr = accountNr;
        this.accountIBAN = accountIBAN;
        this.balance = balance;
        this.frozen = "false";
        this.rewardPoints = 0;
        toDocument();
    }

    //andreea
    public Document toDocument(){//same method to add the user at registration and to update
        //changes in the account or transactions, etc.
        doc = new Document("_id", new ObjectId()).append("first name", this.firstName).
                append("middle name", this.middleName).append("last name", this.lastName).
                append("birth date", this.birthdate).append("personnummer", this.personnummer).
                append("phone number", this. phoneNumber).append("email", this.email).
                append("address", this.address).append("city", this.city).
                append("postal code", this.postalCode).append("university", this.university).
                append("account number", this.accountNr).
                append("account IBAN", this.accountIBAN).append("balance", this.balance).
                append("frozen", this.frozen).append("reward points", this.rewardPoints).
                append("transactions", asList(new Document("sent", ""/*this.account.sent*/), new Document("received", ""/*this.account.received*/))).
                append("loans", asList(new Document("status", ""), new Document("quantity", 0),
                                        new Document("due date", "")));
        return doc;
    }

    //andreea
    public Document toAccounts(){//same method to add the account at registration and to update
        //changes in the account or transactions, etc.
        Document account = new Document("_id", doc.get("_id")).append("account IBAN", this.accountIBAN).
                append("transactions", asList(new Document("activity", this.activity),
                        new Document("pending", this.pending)));
        return account;
    }

    /*//andreea
    public Document toTransactions(){//this method to update the user activity
        Document transaction = new Document("_id", new ObjectId()).append("sender", this.firstName+ " "
                + " " + this.middleName + " " + this.lastName).append("receiver", this.account.).
                append("quantity", "").append("date","").append("concept","");
        return transaction;
    }*/

    public String getFirstName() {
        return this.firstName;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getPersonnummer() {
        return this.personnummer;
    }

    public String getEmail() {
        return this.email;
    }

    public String getAddress() {
        return this.address;
    }

    public String getBirthdate() {
        return this.birthdate;
    }

    public String getUniversity() { return this.university; }

    public void setFirstName(String newName) {
        this.firstName = newName;
    }

    public void setMiddleName(String newName) {
        this.middleName = newName;
    }

    public void setLastName(String newName) {
        this.lastName = newName;
    }

    public void setPhoneNumber(String newPhoneNumber) {
        this.phoneNumber = newPhoneNumber;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public void setTransactions(){}

    public String getBalance() {
        return balance;
    }

    public String getFrozen() {return frozen;}

    /*public int getRewardPoints() { return rewardPoints; }*/

    public String getAccountNr() {
        return accountNr;
    }

    public String getAccountIBAN() {
        return accountIBAN;
    }

    public ArrayList<Transaction> getSent() {
        return activity;
    }

    public ArrayList<Transaction> getReceived() {
        return pending;
    }


    public void setBalance(String balance) {
        this.balance = balance;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public void setSent(ArrayList<Transaction> sent) {
        this.activity = activity;
    }

    public void setReceived(ArrayList<Transaction> received) {
        this.pending = pending;
    }

    public void addPoint(int pointsToAdd) {
        this.rewardPoints += pointsToAdd;
    }

    public void freezeAccount() {
        this.frozen = "true";
    }

    public void unfreezeAccount() {
        this.frozen = "false";
    }

    public void showCard() {
        //get card info and show it
    }
    public String getAllTransactions(){
        String toReturn = "";
        for(int i=0; i<activity.size(); i++){
            Transaction transaction = activity.get(i);
            toReturn.concat(transaction.toString());
        }
        return toReturn;
    }
    //Juan and Ergi
    public String getUsername1(){
        Object key= Mongo.extractKey2("personnummer",getPersonnummer());
        System.out.println(key.toString());
        String username = Mongo.getUsername(key.toString(),"user name").toString();
        return username;
    }
}

