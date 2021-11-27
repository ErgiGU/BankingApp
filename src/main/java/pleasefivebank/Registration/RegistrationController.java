package pleasefivebank.Registration;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RegistrationController {
    private String firstName;
    private String middleName;
    private String lastName;
    private String personalID;
    private String city;
    private String email;
    private String postalCode;
    private String streetName;
    private String userName;
    private String password;
    private String confirmPassword;
    private boolean checkBox;
    private String university;


    //logic, buttons and textfields for page 1
    @FXML
    private Button BackToEntryPageButton;

    @FXML
    private TextField FirstName;

    @FXML
    private TextField LastName;

    @FXML
    private TextField MiddleName;

    @FXML
    private TextField PersonalID;

    @FXML
    private Button ToPage2;

    @FXML
    void BackToEntryPagePressed(ActionEvent event) {
        //logic to go back to entryPage

    }

    @FXML
    void ToPage2Pressed(ActionEvent event) {
        //get input
        firstName = FirstName.getText();
        middleName = MiddleName.getText();
        lastName = LastName.getText();
        personalID = PersonalID.getText();
        //logic to show page 2

    }



    //logic, buttons and textfields for page 2

    @FXML
    private Button BackToPage1;

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
    private Button ToPage3;

    @FXML
    void ToPage1Pressed(ActionEvent event) {

    }

    @FXML
    void ToPage3Pressed(ActionEvent event) {

    }


    //logic, buttons and textfields for page 3


    @FXML
    private Button BackToPage2;

    @FXML
    private PasswordField ConfirmPassword;

    @FXML
    private PasswordField Password;

    @FXML
    private Button ToPage4;

    @FXML
    private TextField UserName;

    @FXML
    private CheckBox checkBoxButton;


    @FXML
    void BackToPage2(ActionEvent event) {

    }

    @FXML
    void ToPage3(ActionEvent event) {

    }

    @FXML
    void checkBoxPressed(ActionEvent event) {

    }


    //buttons and logic for page 4

    @FXML
    private Button BackToPage3;

    @FXML
    private Button FinishRegister;

    @FXML
    private ComboBox<?> UniversityOption;

    @FXML
    void BackToPage3(ActionEvent event) {

    }

    @FXML
    void FinishRegister(ActionEvent event) {

    }

}

