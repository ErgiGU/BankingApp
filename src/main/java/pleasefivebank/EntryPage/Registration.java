package pleasefivebank.EntryPage;

import pleasefivebank.Objects.User;

public class Registration {
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

    //juan
    public boolean register(User newUserInfo){
        //we first see if user exists

        //if he doesnt we create account


        return false;
    }
}
