package pleasefivebank.EntryPage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pleasefivebank.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationController{

    //User newUserInfo = new User();
    Registration registration = new Registration();


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
            String firstName = FirstName.getText();
            if (firstName.isEmpty()){
                firstName = registration.getFirstName();
            }
            else{
                registration.setFirstName(firstName);
            }
            String lastName = LastName.getText();
            if (lastName.isEmpty()){
                lastName = registration.getLastName();
            }
            else{
                registration.setLastName(lastName);
            }
            String middleName = MiddleName.getText();
            if (middleName.isEmpty()){
                middleName = registration.getMiddleName();
            }
            else{
                registration.setMiddleName(middleName);
            }
            String personalID = PersonalID.getText();
            if (personalID.isBlank()){
                personalID = registration.getPersonalID();
            }
            else{
                registration.setPersonalID(personalID);
            }
            if(registration.validatePage1(firstName, middleName, lastName, personalID)){
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

    @FXML
    void Page2to1() {
        try {
            Main.showPage("RegistrationPage1.fxml");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void Page2to3() {
        try {
            String streetName= StreetName.getText();
            if (streetName.isEmpty()){
                streetName = registration.getStreetName();
            }
            else{
                registration.setFirstName(streetName);
            }

            String email= Email.getText();
            if (email.isEmpty()){
                email = registration.getEmail();
            }
            else{
                registration.setEmail(email);
            }

            String city = City.getText();
            if (city.isEmpty()){
                city = registration.getCity();
            }
            else{
                registration.setCity(city);
            }

            String postalCode = PostalCode.getText();
            if (postalCode.isEmpty()){
                postalCode = registration.getPostalCode();
            }
            else{
                registration.setPostalCode(postalCode);
            }

            String phoneNumber = PhoneNumber.getText();
            if (phoneNumber.isEmpty()){
                phoneNumber = registration.getPhoneNumber();
            }
            else{
                registration.setPhoneNumber(phoneNumber);
            }
            if(registration.validatePage2(streetName, email, city, postalCode,phoneNumber)){
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

    @FXML
    void Page3to2(ActionEvent event) {
        try {
            Main.showPage("RegistrationPage2.fxml");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    void Page3to4(ActionEvent event) {
        try {
            String username = UserName.getText();
            if (username.isEmpty()){
                username = registration.getUsername();
            }
            else{
                registration.setUsername(username);
            }

            String password= Password.getText();
            if (password.isEmpty()){
                password = registration.getPassword();
            }

            String confirmPassword= ConfirmPassword.getText();
            if (confirmPassword.isEmpty()){
                confirmPassword = registration.getConfirmPassword();
            }
            if(registration.validatePage3(username,password,confirmPassword) && registration.getCheckbox()){
                Main.showPage("RegistrationPage4.fxml");
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    void checkBoxPressed() {
        registration.changeCheckBox();
    }

    @FXML
    private Button FinishRegister;

    @FXML
    private ComboBox<String> UniversityOption;
    //andreea
    @FXML
    void Select(ActionEvent event) throws IOException {
        String selection = UniversityOption.getSelectionModel().getSelectedItem();
        if (selection.isEmpty()) {
            selection = registration.getUniversity();
        } else{
            registration.setUniversity(selection);
        }
    }
    //juan
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




