package pleasefivebank.Objects;

import java.util.ArrayList;

public class User {
    //variables storing basic user info + arraylist of his transactions + arraylist of his accounts
    private String firstName;
    private int key;
    private String birthdate;
    private String phoneNumber;
    private String personnummer;
    private String email;
    private String address;
    private String city;
    private String postalCode;
    private String middleName;
    private String lastName;
    private ArrayList<Transaction> transactions = new ArrayList<>();
    private ArrayList<Account> accounts = new ArrayList<>();

    public User(String name, String middleName, String lastName, String address, String city, String postalCode, int key, String birthDate, String phoneNumber, String personNummer, String email) {
        this.firstName = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.key = key;
        this.birthdate = birthDate;
        this.phoneNumber = phoneNumber;
        this.personnummer = personNummer;
        this.email = email;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        //we store user transactions from the JSON file in the transactions ArrayList
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

    public int getKey() {
        return this.key;
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
    //override toString
}

