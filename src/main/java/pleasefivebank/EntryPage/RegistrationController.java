package pleasefivebank.EntryPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pleasefivebank.Main;
import pleasefivebank.Objects.User;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class RegistrationController {
    //User newUserInfo = new User();
    EntryPage entryPage = new EntryPage();
    private String firstName = "";
    private String middleName = "";
    private String lastName = "";
    private String personalID = "";
    private String streetName = "";
    private String city = "";
    private String postalCode = "";
    private String email = "";
    private String phoneNumber = "";

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

    //juan
    @FXML
    void Page1to2() {
        try {
            if (firstName.isEmpty() && !FirstName.getText().isEmpty()){
                firstName = FirstName.getText();
            }
            if (lastName.isEmpty() && !LastName.getText().isEmpty()){
                lastName = LastName.getText();
            }
            if (middleName.isEmpty() && !MiddleName.getText().isEmpty()){
                middleName = MiddleName.getText();
            }
            if (personalID.isEmpty() && !PersonalID.getText().isEmpty()){
                personalID = PersonalID.getText();
            }
            if(entryPage.validatePage1(firstName, middleName, lastName, personalID)){
                Main.showPage("RegistrationPage2.fxml");
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
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
    private TextField StreetName;

    //juan
    @FXML
    void Page2to1() {
        try {
            Main.showPage("RegistrationPage1.fxml");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //juan
    @FXML
    void Page2to3() {
        try {
            if (streetName.isEmpty() && !StreetName.getText().isEmpty()){
                streetName = StreetName.getText();
            }
            if (email.isEmpty() && !Email.getText().isEmpty()){
                email = Email.getText();
            }
            if (city.isEmpty() && !City.getText().isEmpty()){
                city = City.getText();
            }
            if (postalCode.isEmpty() && !PostalCode.getText().isEmpty()){
                postalCode = PostalCode.getText();
            }
            if (phoneNumber.isEmpty() && !PhoneNumber.getText().isEmpty()){
                phoneNumber = PhoneNumber.getText();
            }
            if(true){
                Main.showPage("RegistrationPage3.fxml");
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private PasswordField ConfirmPassword;

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

    //juan
    @FXML
    void Page3to4(ActionEvent event) {
        try {
            Main.showPage("RegistrationPage4.fxml");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    void checkBoxPressed() {
        //we gotta make a setter method here


    }

    @FXML
    private Button FinishRegister;

    @FXML
    private ComboBox<?> UniversityOption;

    @FXML
    void FinishRegister() {
        try {
            //you register the user
            //if user selects uni we save it
            Main.showPage("Entry-Page.fxml");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }


    }

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




