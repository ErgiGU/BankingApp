package pleasefivebank.EntryPage;

import com.mongodb.client.FindIterable;
import org.bson.Document;
import pleasefivebank.Mongo;
import pleasefivebank.Objects.User;

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
    private String university;


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
        this.university = "";
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

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
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


    public void setEmail(String Email){
        this.email = Email;
    }

    public void setUsername(String Username){
        this.username = Username;
    }
    public void setPassword(String Password){
        this.password = Password;
    }

    public void setUniversity(String university){this.university = university;}
    
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
    public void register(){
        //String birthdate = extractBirthdate(this.personalID);
        User newUser = new User(this.firstName, this.middleName, this.lastName, this.streetName, this.postalCode,
                this.city, "", "21/12/2009", this.phoneNumber, this.personalID, this.email, this.university);
        //We write the user as document
        Document user = newUser.toDocument();
        //we create a document with encrypted credentials and add it to the database
        Document login = new Document("user name", Mongo.encrypt(this.username)).
                append("password", Mongo.encrypt(this.password));
        Mongo.coll.insertOne(login);
        //get the automatically generated id of the document just inserted
        FindIterable<Document> itr = Mongo.coll.find(login);
        String key = itr.first().get("_id").toString();
        //store the id in the key field of the user document and add the user to the database
        Mongo.coll.insertOne(user.append("key", key));
    }

    //andreea + ossian
    public String extractBirthdate(String personnummer){
        String yearString  = personnummer.substring(0,4);
 /*       if(Integer.parseInt(yearString) > 22){
            yearString += 1990;
        } else {
            yearString += 2000;
        }*/
        String month = personnummer.substring(4,6);
        String day = personnummer.substring(6,8);
        return yearString +"/"+month + "/"+ day;
    }
}