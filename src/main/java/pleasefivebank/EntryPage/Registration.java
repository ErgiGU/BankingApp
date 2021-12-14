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
    public String getUniversity() {return university;}
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
    //ergi and juan
    public boolean validatePage1(String firstName, String middleName, String lastName, String PersonalID){
        return true;
    }
    //ergi and juan
    public boolean validatePage2(String streetname,String email, String city, String postalCode,String phoneNumber){
        return true;
    }
    //ergi and juan
    public boolean validatePage3(String username, String password, String confirmPassword){
        return true;
    }

    //andreea
    public boolean validateData(){
        boolean validate;
        validate = (validatePage1(this.firstName, this.middleName, this.lastName, this.personalID)&&
                validatePage2(this.streetName, this.email, this.city, this.postalCode, this.phoneNumber)&&
                validatePage3(this.username,this.password, this.confirmPassword));
        return validate;
    }

    //andreea
    public boolean register(){
        if(!validateData())//we check if the data is valid
            return false;
        else{ //if he doesn´t exist we create account
            String birthdate = extractBirthdate(this.personalID);
            User newUser = new User(this.firstName, this.middleName, this.lastName, this.streetName, this.postalCode,
                    this.city, "", birthdate, this.phoneNumber,this.personalID,this.email);
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
        return true;
    }

    //andreea
    public String extractBirthdate(String personnummer){
        String yearString  = personnummer.substring(0,4);
        String month = personnummer.substring(4,6);
        String day = personnummer.substring(6,8);
        return yearString+"/"+month + "/"+ day;
    }
}