package pleasefivebank.EntryPage;

import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import pleasefivebank.Mongo;
import pleasefivebank.Objects.Account;
import pleasefivebank.Objects.BasicAccount;
import pleasefivebank.Objects.StudentAccount;
import pleasefivebank.Objects.User;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;

public class Registration{
    private String firstName;
    private String middleName;
    private String lastName;
    private String personalID;

    private String streetName;
    private String city;
    private String postalCode;
    private String email;
    private String phoneNumber;

    private String username;
    private String password;
    private boolean checkbox;
    private String university;
    private Account account;

    //juan
    public Registration(){//in RegistrationController we create an object and then set the attributes values
        this.firstName = "";
        this.middleName = "";
        this.lastName = "";
        this.personalID = "";
        this.streetName = "";
        this.city = "";
        this.postalCode = "";
        this.email = "";
        this.phoneNumber = "";
        this.username = "";
        this.password = "";
        this.checkbox = false;
        this.university = "";
        this.account = null;
    }

    public String getEmail(){
        return email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setPersonalID(String personalID) {
        this.personalID = personalID;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public boolean getCheckbox(){
        return checkbox;
    }

    public String getUniversity() {return university;}


    public void setEmail(String email){
        this.email = email;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setUniversity(String university){this.university = university;}
    
    //juan
    public void changeCheckBox(){
        if(checkbox){
            checkbox = false;
        } else{ checkbox = true; }
    }

    //andreea
    public void setAccount(){
        //generate random IBAN
        String iban = generateIBAN();
        //remove the empty spaces
        iban = iban.replace(" ","");
        String accountNr = iban.substring(7,19);
        //create basic or student account
        if(this.university.isEmpty()){
            this.account = new BasicAccount(accountNr, iban, 0, false);
        } else {
            this.account = new StudentAccount(accountNr, iban, 0, false);
        }
    }

    //andreea
    public void register(){
        String birthdate = extractBirthdate(this.personalID);//set the birthdate
        setAccount();//set the basic or student account
        User newUser = new User(this.firstName, this.middleName, this.lastName, this.streetName, this.postalCode,
                this.city, birthdate, this.phoneNumber, this.personalID, this.email, this.university, this.account);
        //We write the user as document
        Document user = newUser.toDocument();
        //we create a document with encrypted credentials and add it to the database
        Document login = new Document("_id", new ObjectId()).append("user name", this.username).
                append("password", Mongo.encrypt(this.password));
        Mongo.coll.insertOne(login);
        //get the automatically generated id of the document just inserted
        String key = login.get("_id").toString();
        //store the id in the key field of the user document and add the user to the database
        Mongo.coll.insertOne(user.append("key", key));
        //print at the end of file UserDB2
        toFile(user, login, key);
    }

    //andreea && ossian
    public String extractBirthdate(String personnummer){
        String yearString  = personnummer.substring(0,2);
        int year = Integer.parseInt(yearString);
        if(year > 22){ year += 1900; }
        else { year += 2000; }
        String month = personnummer.substring(2,4);
        String day = personnummer.substring(4,6);
        return year +"."+ month + "."+ day;
    }

    //juan
    public String generateIBAN(){
        Iban iban = new Iban.Builder()
                .countryCode(CountryCode.SE)
                .bankCode("555")
                .buildRandom();
        String ibanString = iban.toString();
        return ibanString;
    }

    //andreea
    public void toFile(Document user, Document login, Object key) {
        try {
            FileWriter writer = new FileWriter("UserDB2.json", true);
            writer.write(login.append("_id", key).toJson() + System.lineSeparator());
            writer.write(user.toJson() + System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}