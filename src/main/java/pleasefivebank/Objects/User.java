package pleasefivebank.Objects;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.w3c.dom.events.DocumentEvent;
import pleasefivebank.Mongo;

import java.util.ArrayList;
import java.util.Date;

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
    private Account account;
    private Document doc;

    public User(String name, String middleName, String lastName, String address, String city, String postalCode,
                String birthDate, String phoneNumber, String personNummer, String email, String university,
                Account account) {
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
        this.account = account;
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
                append("account", asList(new Document("account number", this.account.accountNr),
                        new Document("account IBAN", this.account.accountIBAN),
                        new Document("balance", this.account.balance),
                        new Document("frozen", this.account.frozen),
                        new Document("reward points", this.account.rewardPoints))).
                append("transactions", asList(new Document("sent", ""/*this.account.sent*/),
                        new Document("received", ""/*this.account.received*/)));
        if(this.account instanceof StudentAccount) {doc.append("loans",
                asList(new Document("status", ""), new Document("quantity", 0),
                        new Document("due date", "")));}
        return doc;
    }

    //andreea
    public Document toAccounts(){//same method to add the account at registration and to update
        //changes in the account or transactions, etc.
        Document account = new Document("_id", doc.get("_id")).append("account IBAN", this.account.accountIBAN).
                append("transactions", asList(new Document("sent", this.account.sent),
                        new Document("received", this.account.received)));
        return account;
    }

    //andreea
    public Document toTransactions(){//this method to update the user activity
        Document transaction = new Document("_id", new ObjectId()).append("sender", this.firstName+ " "
                + " " + this.middleName + " " + this.lastName).append("receiver", this.account.sent).
                append("quantity", "").append("date","").append("concept","");
        return transaction;
    }

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

    public void setTransactions(){
    }
}

