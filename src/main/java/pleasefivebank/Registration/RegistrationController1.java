package pleasefivebank.Registration;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegistrationController1 {

    @FXML
    private Button BackToEntryPageButton;

    @FXML
    private TextField FirstName;

    @FXML
    private TextField LastName;

    @FXML
    private TextField MiddleName;

    @FXML
    private Button NextPageButton;

    @FXML
    private TextField personalID;

    @FXML
    void BackToEntryPagePressed(ActionEvent event) {
        //connect back to entrypage

    }

    @FXML
    void NextPagePressed(ActionEvent event) {
        //validate input

        //if invalid, stay in the page and push small window to show where user was wrong

        //if valid, save input and connect to registerpage 2

    }

}

