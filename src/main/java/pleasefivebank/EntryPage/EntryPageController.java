
package pleasefivebank.EntryPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Hyperlink;

import java.io.IOException;

public class EntryPageController{

    @FXML
    private PasswordField LoginPassword;

    @FXML
    private TextField LoginUsername;

    @FXML
    private Hyperlink SignUp;

    @FXML
    void PressedLoginButton(ActionEvent event) {
        //get input
        String userName = LoginUsername.getText();
        String password = LoginPassword.getText();
        //validate it
        System.out.println("username: "+userName+" password: "+password);
        //if valid go to user Menu
        
        //if not valid stay in page but show error msg

    }

    @FXML
    void newPage (ActionEvent event) throws IOException {

    }
}




