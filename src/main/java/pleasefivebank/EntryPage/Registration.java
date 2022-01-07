package pleasefivebank.EntryPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import pleasefivebank.Main;
import pleasefivebank.Mongo;
import pleasefivebank.Objects.User;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Timer;

import static pleasefivebank.Main.mainWindow;

public class Registration{

    private boolean checkbox;
    protected String university;
    protected static String tempFirstName="";
    protected static String tempLastName="";
    protected static String tempMiddleName="";
    protected static String tempID="";
    protected static String tempCity="";
    protected static String tempEmail="";
    protected static String tempPhone="";
    protected static String tempPostal="";
    protected static String tempAddress="";
    protected static String tempUsername="";
    protected static String tempPassword="";
    protected static String tempConfirmPassword="";
    protected static String tempUni="";


    public boolean getCheckbox(){
        return checkbox;
    }

    public String getUniversity() {return university;}

    public void setUniversity(String university){this.university = university;}
    
    //juan
    public void changeCheckBox(){
        if(checkbox){
            checkbox = false;
        } else{ checkbox = true; }
    }

    //method to insert the user into the database and the JSON
    //it is called when someone finished to register
    //andreea
    public static void register(User user, String username, String password){
        //String birthdate = extractBirthdate(this.personalID);//set the birthdate
        //We write the user as document
        Document userdoc = user.toDocument();
        //we create a document with encrypted credentials and add it to the database
        Document login = new Document("_id", new ObjectId()).append("user name", username).
                append("password", Mongo.encrypt(password));
        Mongo.coll.insertOne(login);
        //get the automatically generated id of the document just inserted
        String key = login.get("_id").toString();
        //store the id in the key field of the user document and add the user to the database
        Mongo.coll.insertOne(userdoc.append("key", key));
        //print at the end of file UserDB2
        toFile(userdoc, login, key);
        //restart the database to get the new information there
        try {
            Mongo.mongo();//Mongo is a utility class and cannot be instantiated
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Extracts the birthdate from the personnnummer
    //andreea && ossian
    public static String extractBirthdate(String personnummer){
        String yearString  = personnummer.substring(0,2);
        int year = Integer.parseInt(yearString);
        if(year > 22){ year += 1900; }
        else { year += 2000; }
        String month = personnummer.substring(2,4);
        String day = personnummer.substring(4,6);
        return year +"."+ month + "."+ day;
    }

    //This method generates IBAN's for users
    //juan
    public static String generateIBAN(){
        Iban iban = new Iban.Builder()
                .countryCode(CountryCode.SE)
                .bankCode("555")
                .buildRandom();
        String ibanString = iban.toString();
        return ibanString;
    }

    //This method allows to write the new user into the JSON file
    //andreea
    public static void toFile(Document user, Document login, Object key) {
        try {
            FileWriter writer = new FileWriter("UserDB2.json", true);
            writer.write(login.append("_id", key).toJson() + System.lineSeparator());
            writer.write(user.toJson() + System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //this method generates card numbers for new users
    //Carlotta
    public String generateCardNr() {
        Random random = new Random();
        int randomNumberLength = 16;
        int counter = 0;

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < randomNumberLength; i++) {
            int digit = random.nextInt(10);
            builder.append(digit);
            counter++;
            if(counter == 4){
                builder.append(" ");
                counter = 0;
            }
        }
        return builder.toString();
    }

    //this method calculates the expiration date for the card
    //carlotta
    public String calculateExpirationDate(){
        String expirationDate = "";
        String pattern = "MM";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        pattern = "yyyy";
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(pattern);
        String year = simpleDateFormat2.format(new Date());

        int yearInt = Integer.parseInt(year);
        yearInt = yearInt + 5;
        expirationDate=expirationDate.concat(Integer.toString(yearInt)+"-");
        expirationDate=expirationDate.concat(date);

        return expirationDate.substring(2);
    }

    //the following methods gather user information, validate it, check for duplicates in the database
    //and if everything is correct they allow the user to go from page to page

    //Ergi
    public void Page1to2Logic(TextField f, TextField l, TextField m, TextField i, Label fn, Label ln, Label mn, Label id){
        String firstName = f.getText();
        String lastName = l.getText();
        String middleName = m.getText();
        String personalID = i.getText();
        boolean firstNameValidation = DataValidation.validateField(firstName, fn,
                "([\\p{L}]+\s)*[\\p{L}]+", "Enter your real name");
        boolean lastNameValidation = DataValidation.validateField(lastName, ln,
                "([\\p{L}]+\s)*[\\p{L}]+","Enter your real last name");
        boolean idValidation = DataValidation.validateField(personalID,id,
                "\\d{10}","Enter a valid ID");
        boolean middleValidation = DataValidation.validateField(middleName,mn,
                "[a-zA-Z]*","Enter your real middle name");
        //boolean idExists = Mongo.existsInDatabase(personalID,"personnummer",idLabel,"Personal ID already exists")
        if(idValidation) {
            boolean idExists = Mongo.existsInDatabase(personalID,"personnummer",
                    id,"Personal ID already exists");
            if (firstNameValidation && lastNameValidation && middleValidation && !idExists) {
                tempFirstName = firstName;
                tempLastName = lastName;
                tempMiddleName = middleName;
                tempID = personalID;
                try {
                    Main.showPage("RegistrationPage2.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Ergi
    public void page2to3Logic(TextField a, TextField c, TextField p, TextField em,
                              TextField ph, Label ad, Label ci, Label po,Label ema,Label pho){
        String address = a.getText();
        String city = c.getText();
        String postalCode = p.getText();
        String email = em.getText();
        String phone = ph.getText();
        boolean addressVerification = DataValidation.validateField(address, ad, "^\\w+?\\s\\d+$", "Enter a valid address");
        boolean cityVerification = DataValidation.validateField(city, ci, "([\\p{L}]+\s)*[\\p{L}]+", "Enter a valid city name");
        //RFC 5322 Official Standard
        boolean emailVerification = DataValidation.validateField(email, ema, "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[" +
                "\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)" +
                "+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[" +
                "\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", "Wrong email format");
        boolean phoneVerification = DataValidation.validateField(phone, pho, "\\d{10}", "Enter a valid phone number");
        //boolean phoneExists = Mongo.existsInDatabase(phone,"phone number",phoneLabel,"Phone is already registered" );
        boolean postalVerification = DataValidation.validateField(postalCode, po, "\\d{5}", "Enter a valid postal code");
        if (emailVerification) {
            boolean checkIfEmailExists = Mongo.existsInDatabase(email, "email", ema, "Email already exists");
            if (phoneVerification && !checkIfEmailExists) {
                boolean phoneExists = Mongo.existsInDatabase(phone, "phone number", pho, "Phone is already registered");
                if (addressVerification && cityVerification && !phoneExists && postalVerification ) {
                    try {
                        tempAddress = address;
                        tempCity = city;
                        tempPostal = postalCode;
                        tempEmail = email;
                        tempPhone = phone;
                        Main.showPage("RegistrationPage3.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    //Ergi
    public void page3to4Logic(TextField usern, TextField pass, TextField confPass,
                              Label usernLbl,Label passLbl,Label confLbl){
        String username = usern.getText();
        String password = pass.getText();
        String confirmPassword = confPass.getText();
        boolean usernameValidation = DataValidation.validateField(username,usernLbl,
                "^[A-Za-z][A-Za-z0-9_]{7,29}$","The username must be at least 8 characters");
        boolean passwordValidation = DataValidation.validateField(password, passLbl,
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,30}$",
                "Invalid password format");
        if(usernameValidation){
            boolean usernameExists = Mongo.existsInDatabase(username, "user name",usernLbl,"Username already exists");
            if(passwordValidation && !usernameExists) {
                boolean passwordsMatch = DataValidation.passwordsMatch(password,confirmPassword,confLbl,"Passwords must match");
                if (passwordsMatch && getCheckbox()) {
                    tempUsername = username;
                    tempPassword = password;
                    try {
                        Main.showPage("RegistrationPage4.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    //this method calls the method to make the user
    //it also finishes registering the user
    //and after registration is done it shows the confirmation page
    //Ergi
    public void FinishRegisterLogic(){
        try {

            String iban = Registration.generateIBAN();
            String accNr = iban.substring(7,19);
            Random rand = new Random();
            double r = rand.nextDouble(8000000);
            String random = Double.toString(r);

            User user = new User(generateCardNr(), calculateExpirationDate() ,tempFirstName,tempMiddleName,tempLastName,tempAddress,tempCity,tempPostal,Registration.extractBirthdate(tempID),
                    tempPhone,tempID,tempEmail,tempUni,accNr,iban,random,"false");
            Registration.register(user,tempUsername,tempPassword);
            Mongo.mongo();
            Main.showPage("RegisterConfirmation.fxml");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //juan && Ergi
    //This method displays the information that user had put on previous pages to
    //allow the user to go back to other pages and correct information

    public static void backToPage(String screen,int screenNumber) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(screen));
        Scene scene = new Scene(fxmlLoader.load());
        RegistrationController registrationController = fxmlLoader.getController();
        switch (screenNumber) {
            case 1 -> registrationController.setData1();
            case 2 -> registrationController.setData2();
            case 3 -> registrationController.setData3();
        }
        mainWindow.setScene(scene);
    }


}