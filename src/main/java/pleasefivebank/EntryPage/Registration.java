package pleasefivebank.EntryPage;

import com.mongodb.client.FindIterable;
import org.bson.Document;
import pleasefivebank.Mongo;
import pleasefivebank.Objects.User;

import java.util.Base64;

import static com.mongodb.client.model.Filters.eq;

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
    private String confirmPassword;
    private boolean checkbox;


    //juan
    public Registration(){
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
        this.confirmPassword = "";
        this.checkbox = false;
    }
    //juan
    public String getFirstName(){
        return firstName;
    }
    public String getMiddleName(){
        return middleName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getPersonalID(){
        return personalID;
    }
    public String getStreetName(){
        return streetName;
    }
    public String getCity(){
        return city;
    }
    public String getPostalCode(){
        return postalCode;
    }
    public String getEmail(){
        return email;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String getConfirmPassword(){
        return confirmPassword;
    }
    public boolean getCheckbox(){
        return checkbox;
    }
    public void setFirstName(String FirstName){
        this.firstName = FirstName;
    }
    public void setMiddleName(String MiddleName){
        this.middleName = MiddleName;
    }
    public void setLastName(String LastName){
        this.lastName = LastName;
    }
    public void setPersonalID(String PersonalID){
        this.personalID = PersonalID;
    }
    public void setCity(String City){
        this.city = City;
    }
    public void setPostalCode(String PostalCode){
        this.postalCode = PostalCode;
    }
    public void setStreetName(String StreetName){
        this.streetName = StreetName;
    }
    public void setEmail(String Email){
        this.email = Email;
    }
    public void setPhoneNumber(String PhoneNumber){
        this.phoneNumber = PhoneNumber;
    }
    public void setUsername(String Username){
        this.username = Username;
    }
    public void setPassword(String Password){
        this.password = Password;
    }
    public void setConfirmPassword(String ConfirmPassword){
        this.confirmPassword = ConfirmPassword;
    }

    
    //juan
    public void changeCheckBox(){
        if(checkbox){
            checkbox = false;
        }
        else{
            checkbox = true;
        }

    }

    //andreea
    public boolean register(){
        //we first see if user exists
        if(Mongo.isUser(this.username))
            return false;
        else{ //if he doesn´t we create account
            String birthdate = extractBirthdate(this.personalID);
            User newUser = new User(this.firstName, this.middleName, this.lastName, this.streetName, this.postalCode,
                    this.city, "", birthdate, this.phoneNumber,this.personalID,this.email);
            //We add the user to the database as document
            Document user = newUser.toDocument();
            //we create a document with encrypted credentials and add it to the database
            Document doc = new Document("user name", Mongo.encrypt(this.username)).
                    append("password", Mongo.encrypt(this.password));
            Mongo.coll.insertOne(doc);
            //get the automatically generated id of the document just inserted
            FindIterable<Document> itr = Mongo.coll.find(doc);
            String key = itr.first().get("_id").toString();
            //store the id in the key field of the user document
            Mongo.coll.findOneAndUpdate(eq("personnummer", this.personalID),
                    new Document("$set", new Document("key", key)));
        }
        return true;
    }

    //andreea
    public String extractBirthdate(String personnummer){
        String birthday =  personnummer.substring(0, 8);
        String yearString  = personnummer.substring(0,4);
        String month = personnummer.substring(4,6);
        String day = personnummer.substring(6,8);
        return yearString+"/"+month + "/"+ day;
    }
}