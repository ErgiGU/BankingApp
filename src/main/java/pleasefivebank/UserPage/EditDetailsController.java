package pleasefivebank.UserPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import pleasefivebank.EntryPage.DataValidation;
import pleasefivebank.Main;
import pleasefivebank.Mongo;
import pleasefivebank.Objects.User;

import java.io.IOException;

import static pleasefivebank.EntryPage.EntryPageController.user;

public class EditDetailsController {

    @FXML
    private Label addressLabel;

    @FXML
    private Label cityLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label postalLabel;

    @FXML
    private Text IBANlabel;

    @FXML
    private Text accountFrozenLabel;

    @FXML
    private Text accountnumberLabel;

    @FXML
    private TextField cityTextfield;

    @FXML
    private TextField postalcodeTextfield;

    @FXML
    private TextField streetTextfield;

    @FXML
    private TextField emailTextfield;

    @FXML
    private Label firstnameLabel;

    @FXML
    private Label lastnameLabel;

    @FXML
    private Text personalidLabel;

    @FXML
    private TextField phonenumberTextfield;

    @FXML
    private TextField universityTextfield;

    @FXML
    private TextField usernameTextfield;

    //juan
    @FXML
    void SaveChanges(ActionEvent event) {
        //get user input and validate with regex
        String username = usernameTextfield.getText();
        boolean usernameValidation = DataValidation.validateField(username,usernameLabel,
                "^[A-Za-z][A-Za-z0-9_]{7,29}$","The username must be at least 8 characters");
        boolean usernameExists = Mongo.existsInDatabase(username, "user name",usernameLabel,"Username already exists");

        String phoneNumber = phonenumberTextfield.getText();
        boolean phoneVerification = DataValidation.validateField(phoneNumber, phoneLabel, "\\d{10}", "Enter a valid phone number");
        boolean phoneExists = Mongo.existsInDatabase(phoneNumber, "phone number", phoneLabel, "Phone is already registered");

        String email = emailTextfield.getText();
        boolean emailVerification = DataValidation.validateField(email, emailLabel, "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[" +
                "\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)" +
                "+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[" +
                "\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", "Wrong email format");
        boolean checkIfEmailExists = Mongo.existsInDatabase(email, "email", emailLabel, "Email already exists");

        String street = streetTextfield.getText();
        boolean addressVerification = DataValidation.validateField(street, addressLabel, "^\\w+?\\s\\d+$", "Enter a valid address");

        String postal = postalcodeTextfield.getText();
        boolean postalVerification = DataValidation.validateField(postal, postalLabel, "\\d{5}", "Enter a valid postal code");

        String city = cityTextfield.getText();
        boolean cityVerification = DataValidation.validateField(city, cityLabel, "([\\p{L}]+\s)*[\\p{L}]+", "Enter a valid city name");
        //im lacking this one cause we have to discuss how to do regex
        //id rather change this to an optionbox as we cant rely on users to write their uni perfectly
        String uni = universityTextfield.getText();

        //if data is valid and doesnt use other users unique data make changes efective
        if(usernameValidation && phoneVerification && emailVerification && addressVerification &&
        postalVerification && cityVerification && !phoneExists && !checkIfEmailExists && !usernameExists){
            //user.setUsername
            user.setPhoneNumber(phoneNumber);
            user.setEmail(email);
            user.setCity(city);
            user.setPostalCode(postal);
            user.setAddress(street);
            //user.setUni
            //update json with lottis method

            //update db by calling the mongo db lines
        }






        user.setCity(cityTextfield.getText());


    }

    @FXML
    void toHomePage(ActionEvent event) {
        try {
            Main.showLoginPage(user.getFirstName()+ " " + user.getLastName());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void setData(String fullName){
        firstnameLabel.setText(user.getFirstName());
        lastnameLabel.setText(user.getLastName());
        IBANlabel.setText(user.getAccountIBAN());
        accountFrozenLabel.setText(user.getFrozen());
        accountnumberLabel.setText(user.getAccountNr());
        cityTextfield.setText(user.getCity());
        postalcodeTextfield.setText(user.getPostalCode());
        streetTextfield.setText(user.getAddress());
        emailTextfield.setText(user.getEmail());
        personalidLabel.setText(user.getPersonnummer());
        phonenumberTextfield.setText(user.getPhoneNumber());
        universityTextfield.setText(user.getUniversity());
        usernameTextfield.setText(user.getUsername1());
    }

}

