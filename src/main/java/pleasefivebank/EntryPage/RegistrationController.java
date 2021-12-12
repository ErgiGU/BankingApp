package pleasefivebank.EntryPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pleasefivebank.Main;

import java.io.IOException;
import java.util.HashMap;

public class RegistrationController {
    HashMap<String, String> newUserInfo = new HashMap();
    EntryPage entryPage = new EntryPage();

    @FXML
    private TextField FirstName;

    @FXML
    private TextField LastName;

    @FXML
    private TextField MiddleName;

    @FXML
    private TextField PersonalID;

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
    void Page1to2() {
        try {
            String firstName = FirstName.getText();
            if (firstName.isEmpty()){
                firstName = newUserInfo.get("firstName");
            }
            String lastName = FirstName.getText();
            if (lastName.isEmpty()){
                lastName = newUserInfo.get("lastName");
            }
            String middleName = MiddleName.getText();
            if (middleName.isEmpty()){
                middleName = newUserInfo.get("middleName");
            }
            String personalID = PersonalID.getText();
            if (firstName.isEmpty()){
                personalID = newUserInfo.get("personalID");
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
            Main.showPage("RegistrationPage3.fxml");
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




