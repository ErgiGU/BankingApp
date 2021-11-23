package pleasefivebank.EntryPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class EntryPageController {

    @FXML
    private Button LoginButton;

    @FXML
    private PasswordField LoginPassword;

    @FXML
    private TextField LoginUsername;

    @FXML
    void LoginButtonPressed(ActionEvent event) {
        String userName = LoginUsername.getText();
        String Password = LoginUsername.getText();
        //System.out.println(userName+ " password: " + Password);
        

    }

}
