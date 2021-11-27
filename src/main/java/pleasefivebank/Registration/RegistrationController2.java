package pleasefivebank.Registration;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegistrationController2 {

    @FXML
    private Button BackPageButton;

    @FXML
    private Button BackToEntryPageButton;

    @FXML
    private TextField City;

    @FXML
    private TextField Email;

    @FXML
    private Button NextPageButton;

    @FXML
    private TextField PhoneNumber;

    @FXML
    private TextField PostalCode;

    @FXML
    private TextField StreetName;

    @FXML
    void BackPagePressed(ActionEvent event) {
        //connect to registrationPage1
    }

    @FXML
    void BackToEntryPagePressed(ActionEvent event) {
        //connect to EntryPage
    }

    @FXML
    void NextPagePressed(ActionEvent event) {
        //get input
        String  city = City.getText();
        String  email = Email.getText();
        String phoneNumber = PhoneNumber.getText();
        String  postalCode = PostalCode.getText();
        String  streetName = StreetName.getText();
        //validate input

        //if invalid, stay in the page and push small window to show where user was wrong

        //if valid, save input and connect to registerpage 3


    }

}

