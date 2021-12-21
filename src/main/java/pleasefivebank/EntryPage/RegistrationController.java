package pleasefivebank.EntryPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import pleasefivebank.Main;
import pleasefivebank.Mongo;
import pleasefivebank.Objects.Account;

import java.io.FileWriter;
import java.io.IOException;

public class RegistrationController {

    private Registration registration = new Registration();

    private static String tempFirstName="";
    private static String tempLastName="";
    private static String tempMiddleName="";
    private static String tempID="";
    private static String tempCity="";
    private static String tempEmail="";
    private static String tempPhone="";
    private static String tempPostal="";
    private static String tempAddress="";
    private static String tempUsername="";
    private static String tempPassword="";
    private static String tempConfirmPassword="";

    @FXML
    private TextField FirstName;

    @FXML
    private TextField LastName;

    @FXML
    private TextField MiddleName;

    @FXML
    private TextField PersonalID;

    //juan
    @FXML
    void BackToEntryPage() {
        try {
            Main.showPage("Entry-Page.fxml");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Label cityLabel;

    @FXML
    private Label postalLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Label middleNameLabel;

    //Ergi
    @FXML
    void Page1to2() {
        String firstName = FirstName.getText();
        String lastName = LastName.getText();
        String middleName = MiddleName.getText();
        String personalID = PersonalID.getText();
        boolean firstNameValidation = DataValidation.validateField(firstName, firstNameLabel,
                "([\\p{L}]+\s)*[\\p{L}]+", "Enter your real name");
        boolean lastNameValidation = DataValidation.validateField(lastName, lastNameLabel,
                "([\\p{L}]+\s)*[\\p{L}]+","Enter your real last name");
        boolean idValidation = DataValidation.validateField(personalID,idLabel,
                "\\d{10}","Enter a valid ID");
        boolean middleValidation = DataValidation.validateField(middleName,middleNameLabel,
                "[a-zA-Z]*","Enter your real middle name");
        //boolean idExists = Mongo.existsInDatabase(personalID,"personnummer",idLabel,"Personal ID already exists")
        if(idValidation) {
            boolean idExists = Mongo.existsInDatabase(personalID,"personnummer",
                    idLabel,"Personal ID already exists");
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

    @FXML
    private TextField City;

    @FXML
    private TextField Email;

    @FXML
    private TextField PhoneNumber;

    @FXML
    private TextField PostalCode;

    @FXML
    private TextField Address;

    //juan && Ergi
    @FXML
    void Page2to1() {
        try {
            Main.showPage("RegistrationPage1.fxml");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //Ergi
    @FXML
    void Page2to3() {
        String address = Address.getText();
        String city = City.getText();
        String postalCode = PostalCode.getText();
        String email = Email.getText();
        String phone = PhoneNumber.getText();
        boolean addressVerification = DataValidation.validateField(address, addressLabel, "^\\w+?\\s\\d+$", "Enter a valid address");
        boolean cityVerification = DataValidation.validateField(city, cityLabel, "([\\p{L}]+\s)*[\\p{L}]+", "Enter a valid city name");
        //RFC 5322 Official Standard
        boolean emailVerification = DataValidation.validateField(email, emailLabel, "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[" +
                "\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)" +
                "+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[" +
                "\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", "Wrong email format");
        boolean phoneVerification = DataValidation.validateField(phone, phoneLabel, "\\d{10}", "Enter a valid phone number");
        //boolean phoneExists = Mongo.existsInDatabase(phone,"phone number",phoneLabel,"Phone is already registered" );
        boolean postalVerification = DataValidation.validateField(postalCode, postalLabel, "\\d{5}", "Enter a valid postal code");
        if (emailVerification) {
            boolean checkIfEmailExists = Mongo.existsInDatabase(email, "email", emailLabel, "Email already exists");
            if (phoneVerification && !checkIfEmailExists) {
                boolean phoneExists = Mongo.existsInDatabase(phone, "phone number", phoneLabel, "Phone is already registered");
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

    @FXML
    private PasswordField ConfirmPassword;

    @FXML
    private Label confirmLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private PasswordField Password;

    @FXML
    private TextField UserName;

    //juan
    @FXML
    void Page3to2(ActionEvent event) {
        try {
            Main.showPage("RegistrationPage2.fxml");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //Ergi and juan
    @FXML
    void Page3to4(ActionEvent event) {
        String username = UserName.getText();
        String password = Password.getText();
        String confirmPassword = ConfirmPassword.getText();
        boolean usernameValidation = DataValidation.validateField(username,usernameLabel,
                "^[A-Za-z][A-Za-z0-9_]{7,29}$","The username must be at least 8 characters");
        boolean passwordValidation = DataValidation.validateField(password, passwordLabel,
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,30}$",
                "Password must contain at least one(number,digit,uppercase,lowercase,special character)");
        //boolean usernameExists = Mongo.existsInDatabase(username, "user name",usernameLabel,"Username already exists");
        if(passwordValidation) {
            boolean passwordsMatch = DataValidation.passwordsMatch(password,confirmPassword,confirmLabel,"Passwords must match");
            if (usernameValidation && passwordsMatch && registration.getCheckbox()) {
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

    //juan
    @FXML
    void checkBoxPressed() {
        registration.changeCheckBox();
    }

    @FXML
    private Button FinishRegister;

    @FXML
    private ComboBox<String> UniversityOption;

    @FXML
    void Select(ActionEvent event) throws IOException {
        String selection = UniversityOption.getSelectionModel().getSelectedItem();
        registration.setUniversity(selection);
    }

    //juan
    @FXML
    void FinishRegister(ActionEvent event) {
        try {
            //you register the user
            //if user selects uni we save it
            registration.setFirstName(tempFirstName);
            registration.setLastName(tempLastName);
            registration.setMiddleName(tempMiddleName);
            registration.setPersonalID(tempID);
            registration.setStreetName(tempAddress);
            registration.setCity(tempCity);
            registration.setPostalCode(tempPostal);
            registration.setPhoneNumber(tempPhone);
            registration.setEmail(tempEmail);
            registration.setUsername(tempUsername);
            registration.setPassword(tempPassword);
            //registration.setAccount();
            registration.register();
            Main.showPage("Entry-Page.fxml");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //juan
    @FXML
    void Page4to3(ActionEvent event) {
        try {
            Main.showPage("RegistrationPage3.fxml");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}




