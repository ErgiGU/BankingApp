package pleasefivebank.Registration;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrationController3 {
    boolean checkbox = false;

    @FXML
    private Button BackButton;

    @FXML
    private Button BackToEntryPageButton;

    @FXML
    private PasswordField ConfirmPassword;

    @FXML
    private Button NextButton;

    @FXML
    private PasswordField Password;

    @FXML
    private TextField UserName;

    @FXML
    private CheckBox checkBoxButton;

    @FXML
    void BackPressed(ActionEvent event) {
        //connect to registrationPage2

    }

    @FXML
    void BackToEntryPagePressed(ActionEvent event) {
        //connect to registrationPage3
    }

    @FXML
    void NextPressed(ActionEvent event) {
        //get input
        String  userName = UserName.getText();
        String  password = Password.getText();
        String  confirmPassword  = ConfirmPassword.getText();

        //validate input
        //method must also be called with checkbox boolean to check if user pressed it

        //if invalid, stay in the page and push small window to show where user was wrong

        //if valid, save input and connect to registerpage 3


    }

    @FXML
    void checkBoxPressed(ActionEvent event) {
        checkbox = true;
    }

}

